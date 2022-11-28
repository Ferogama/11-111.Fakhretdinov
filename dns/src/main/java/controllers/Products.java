package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import listeners.InitListener;
import models.Product;
import models.User;
import models.UserProduct;
import repositories.ProductRepository;
import repositories.UserProductRepository;
import service.ProductService;
import service.UserProductService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/products")
public class Products extends HttpServlet {

    private final ProductService productService = new ProductService();
    private final UserProductService userProductService = new UserProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Product> allProducts = productService.findAllProducts();

        User user = null;
        if (request.getSession(false) != null) {
            user = (User) request.getSession(false).getAttribute("user");
        }
        request.setAttribute("isAdmin", user != null && user.getRole().equals("admin"));
        request.setAttribute("products", allProducts);

        request.getRequestDispatcher("/WEB-INF/jsp/products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession(false).getAttribute("user");
        if (user == null) {
            response.sendRedirect("/authorization");
            return;
        }
        try {
            Long productId = Long.parseLong(request.getParameter("productId"));
            InitListener.cart.add(productId);
            Map<Long, Long> cartMap =
                    InitListener.cart.stream().collect(Collectors.groupingBy(el -> el, Collectors.counting()));

            UserProduct userProduct = UserProduct.builder()
                    .productId(productId)
                    .userId(user.getId())
                    .build();

            userProductService.save(userProduct);

            response.sendRedirect("/products");

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
