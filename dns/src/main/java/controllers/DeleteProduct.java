package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.CommentRepository;
import repositories.ProductRepository;
import repositories.UserProductRepository;
import service.CommentService;
import service.ProductService;
import service.UserProductService;
import service.UserService;

import java.io.IOException;

@WebServlet("/products/delete")
public class DeleteProduct extends HttpServlet {

    private final ProductService productService = new ProductService();
    private final UserProductService userProductService = new UserProductService();
    private final CommentService commentService = new CommentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long productId = Long.parseLong(req.getParameter("deleteProductId"));


            userProductService.findAll().forEach(userProduct -> {
                if (userProduct.getProductId().equals(productId)) {
                    userProductService.deleteByProductID(productId);
                }
            });

            commentService.findAll().forEach(comment -> {
                if (comment.getProductId().equals(productId)) {
                    commentService.deleteComment(comment.getProductId());
                }
            });

            productService.deleteProduct(productId);
            resp.sendRedirect("/products");
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
