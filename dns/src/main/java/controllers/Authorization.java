package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.User;
import repositories.UserRepository;
import service.UserService;
import utils.MD5HashFunction;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Authorization", value = "/authorization")
public class Authorization extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/authorization.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = MD5HashFunction.hashPassword(request.getParameter("pass"));

        List<User> userList = userService.findAllUsers();

        for (User user : userList) {
            if (user.getLogin().equals(login)) {
                if (user.getPassword().equals(password)) {
                    request.getSession(true).setAttribute("user", user);
                    response.sendRedirect("/main");
                    return;
                }
            }
        }
        response.sendRedirect("/authorization");
    }
}
