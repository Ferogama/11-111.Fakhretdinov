package service;

import models.Product;
import repositories.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductService {
    private static final ProductRepository repository = new ProductRepository();

    public void saveProduct(Product product) {
        repository.save(product);
    }

    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    public void updateProduct(Product product) {
        repository.update(product);
    }

    public void deleteProduct(Long id) {
        repository.delete(id);
    }

    public Product getProduct(Long id) {
        Optional<Product> product = repository.getById(id);
        if (product.isPresent()) {
            return  product.get();
        }

        throw new IllegalArgumentException("No such product");
    }


    public static String getNameById(Long id) {
        Optional<Product> optionalProduct = repository.getById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get().getName();
        } else {
            throw new IllegalArgumentException("No such product");
        }
    }

    public static Long getPrice(Long id, Long count) {
        Optional<Product> optionalProduct = repository.getById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get().getPrice() * count;
        } else {
            throw new IllegalArgumentException("No such product");
        }
    }

    public static Long getTotalPrice(Map<Long,Long> map) {
        long sum = 0L;
        for (Long key : map.keySet()) {
            sum += getPrice(key, map.get(key));
        }

        return sum;
    }
}
