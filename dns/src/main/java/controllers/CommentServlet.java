package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Comment;
import models.Product;
import models.User;
import repositories.CommentRepository;
import repositories.ProductRepository;
import service.CommentService;
import service.ProductService;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/comment/*")
public class CommentServlet extends HttpServlet {

    private final ProductService productService = new ProductService();
    private final CommentService commentService = new CommentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long productId = Long.parseLong(req.getPathInfo().substring(1));


            List<Comment> comments = commentService.findAll().stream()
                    .filter(comment -> comment.getProductId().equals(productId))
                    .collect(Collectors.toList());

            req.setAttribute("comments", comments);
            req.getRequestDispatcher("/WEB-INF/jsp/comment.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("comment");
        User user = (User) req.getSession(false).getAttribute("user");

        Long productId = Long.parseLong(req.getPathInfo().substring(1));

        Comment comment = Comment.builder()
                .text(text)
                .userName(user.getLogin())
                .productId(productId)
                .build();

        commentService.saveComment(comment);

        resp.sendRedirect("/comment/" + productId);
    }
}
