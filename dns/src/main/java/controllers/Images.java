package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Product;
import service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/images/*")
public class Images extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imgName = req.getPathInfo().substring(1);
        List<Product> products = productService.findAllProducts();

        for (Product product : products) {
            if (product.getImgName().equals(imgName)) {
                byte[] img = product.getImg();
                resp.setContentType(getServletContext().getMimeType(imgName));
                resp.setContentLength(img.length);
                resp.getOutputStream().write(img);
                return;
            }
        }
    }
}
