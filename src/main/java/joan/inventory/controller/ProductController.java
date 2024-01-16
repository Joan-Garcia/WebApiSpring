package joan.inventory.controller;

import joan.inventory.exception.NotFoundException;
import joan.inventory.model.Product;
import joan.inventory.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        logger.info("Getting products...");
        return this.productService.getAll();
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        logger.info("Create new product: " + product);
        return this.productService.saveProduct(product);
    }

    @GetMapping("/products/{idProduct}")
    public ResponseEntity<Product> getProductById(@PathVariable int idProduct) {
        logger.info("Getting product by id: " + idProduct);
        Product product = this.productService.getProduct(idProduct);

        if (product == null) throw new NotFoundException("Product not found");

        return ResponseEntity.ok(product);
    }

    @PutMapping("/products/{idProduct}")
    public ResponseEntity<Product> updateProduct(@PathVariable int idProduct, @RequestBody Product product) {
        product.setIdProduct(idProduct);
        this.productService.saveProduct(product);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{idProduct}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable int idProduct) {
        Product product = this.productService.getProduct(idProduct);

        if (product == null) throw new NotFoundException("Product not found");

        this.productService.deleteProductById(idProduct);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
