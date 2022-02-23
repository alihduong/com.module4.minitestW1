package mn.repository;

import mn.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ProductRepository implements IProductRepository{

    @Autowired
    private IProductRepository productRepository;
    @Override
    public ArrayList<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    @Override
    public Product deleteProduct(int id) {
        return productRepository.deleteProduct(id);
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findProductById(id);
    }
}
