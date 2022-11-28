package service;

import models.UserProduct;
import repositories.UserProductRepository;

import java.util.List;

public class UserProductService {

    private static final UserProductRepository repository = new UserProductRepository();

    public void deleteByProductID(Long id) {
        repository.deleteByProductId(id);
    }

    public List<UserProduct> findAll() {
        return repository.findAll();
    }

    public void updateProduct(UserProduct userProduct) {
        repository.update(userProduct);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public void save(UserProduct userProduct) {
        repository.save(userProduct);
    }
}
