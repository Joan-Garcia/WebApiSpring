package joan.inventory.service;

import joan.inventory.model.Product;
import joan.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProduct(Integer idProduct) {
        return this.productRepository.findById(idProduct).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void deleteProductById(Integer idProduct) {
        this.productRepository.deleteById(idProduct);
    }
}
