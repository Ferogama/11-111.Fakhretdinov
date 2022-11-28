package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.UserProductRepository;
import service.UserProductService;

import java.io.IOException;

@WebServlet("/cart/delete")
public class DeleteFromCart extends HttpServlet {

    private final UserProductService userProductService = new UserProductService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long productId = Long.parseLong(req.getParameter("deleteProduct"));

            userProductService.deleteByProductID(productId);
            resp.sendRedirect("/cart");
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
