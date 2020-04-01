package pl.edu.wszib.dao;

import pl.edu.wszib.domain.Product;
import pl.edu.wszib.domain.User;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts();
    void removeProduct(Long id);
    void saveProduct(Product product);
    Product getById(Long id);
}
