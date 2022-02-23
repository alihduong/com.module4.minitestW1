package mn.repository;

import mn.model.Product;

import java.util.ArrayList;

public interface IProductRepository {
    ArrayList<Product> findAll();

    Product saveProduct(Product product);

    Product deleteProduct(int id);
    Product findProductById(int id);
}
