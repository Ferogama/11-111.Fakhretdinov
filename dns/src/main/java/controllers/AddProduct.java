package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Product;
import repositories.ProductRepository;
import service.ProductService;

import java.io.IOException;

@WebServlet("/products/add")
@MultipartConfig
public class AddProduct extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/add_product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Part image = request.getPart("img");
        String fileName = image.getSubmittedFileName();

        try {
            Integer price = Integer.parseInt(request.getParameter("price"));
            Product product = Product.builder()
                    .name(name)
                    .description(description)
                    .price(price)
                    .img(image.getInputStream().readAllBytes())
                    .imgName(fileName)
                    .build();

            productService.saveProduct(product);

        } catch (Exception e) {
            response.sendRedirect("/products/add");
            return;
        }

        response.sendRedirect("/main");
    }
}
