package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Product;
import repositories.ProductRepository;
import service.ProductService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/products/edit")
public class EditProduct extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long productId = Long.parseLong(req.getParameter("updateProductId"));
            Product product = productService.getProduct(productId);

            req.setAttribute("product", product);

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/edit_product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            Integer price = Integer.parseInt(req.getParameter("price"));
            Long productId = Long.parseLong(req.getParameter("updateProductId"));

            Product product = Product.builder()
                    .id(productId)
                    .name(name)
                    .description(description)
                    .price(price)
                    .build();

            productService.updateProduct(product);

            resp.sendRedirect("/products");
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
