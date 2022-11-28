package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.User;
import models.UserProduct;
import repositories.UserProductRepository;
import repositories.UserRepository;
import service.UserProductService;
import service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/cart")
public class Cart extends HttpServlet {

    private final UserService userService = new UserService();
    private final UserProductService userProductService = new UserProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<UserProduct> userProducts = userProductService.findAll();

        if (userProducts.size() == 0) {
            response.sendRedirect("/products");
            return;
        }

        Map<Long, Long> map = userProducts.stream()
                .filter(userProduct -> Objects.equals(userProduct.getUserId(), user.getId()) && !userProduct.isProcessed())
                .collect(Collectors.groupingBy(UserProduct::getProductId, Collectors.counting()));

        request.setAttribute("map", map);
        request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer totalSum = Integer.parseInt(request.getParameter("totalSum"));
            User user = (User) request.getSession().getAttribute("user");

            List<UserProduct> userProducts = userProductService.findAll();

            if (user.getBalance() < totalSum) {
                response.sendRedirect("/cart?error");
                return;
            }

            user.setBalance(user.getBalance() - totalSum);
            userService.updateUser(user);


            for (UserProduct userProduct : userProducts) {
                userProduct.setProcessed(true);
                userProductService.updateProduct(userProduct);
            }

            response.sendRedirect("/cart");

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
