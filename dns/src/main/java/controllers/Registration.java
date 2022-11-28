package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import repositories.UserRepository;
import service.UserService;
import utils.MD5HashFunction;

import java.io.IOException;
import java.util.List;

@WebServlet("/registration")
public class Registration extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = MD5HashFunction.hashPassword(req.getParameter("pass"));

        boolean alreadyExists = userService.findAllUsers().stream()
                .anyMatch(user -> user.getLogin().equals(login));

        if (alreadyExists) {
            resp.sendRedirect("/registration?error");
            return;
        }

        User user = User.builder()
                .login(login)
                .password(password)
                .build();

        userService.saveUser(user);

        resp.sendRedirect("/authorization");
    }
}
