package ravi.learning.productcatalogservice.services;

import ravi.learning.productcatalogservice.dto.ProductDto;
import ravi.learning.productcatalogservice.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public Product getSingleProduct(Long productId);

    public ProductDto addNewProduct(ProductDto product);

    public Product  updateProduct(Long productId, Product product);
    public boolean deleteProduct(Long productId);
}
