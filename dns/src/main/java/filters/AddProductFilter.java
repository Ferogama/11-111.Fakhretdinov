package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;

@WebFilter(urlPatterns = "/products/add")
public class AddProductFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        if (request.getSession(false) == null) {
            response.sendRedirect("/main");
            return;
        }

        User user = (User) request.getSession(false).getAttribute("user");

        if (user == null) {
            response.sendRedirect("/main");
            return;
        } else if (!user.getRole().equals("admin")) {
            response.sendRedirect("/main");
            return;
        }

        chain.doFilter(request, response);
    }
}
