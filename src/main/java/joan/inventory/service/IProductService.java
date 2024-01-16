package joan.inventory.service;

import joan.inventory.model.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getAll();

    public Product getProduct(Integer idProduct);

    public Product saveProduct(Product product);

    public void deleteProductById(Integer idProduct);
}
