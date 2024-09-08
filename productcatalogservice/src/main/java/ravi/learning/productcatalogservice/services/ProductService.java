package ravi.learning.productcatalogservice.services;

import ravi.learning.productcatalogservice.dto.ProductDto;
import ravi.learning.productcatalogservice.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAllProducts();

    public Optional<Product> getSingleProduct(Long productId);

    public ProductDto addNewProduct(ProductDto product);

    public ProductDto  updateProduct(Long productId, ProductDto product);
    public ProductDto deleteProduct(Long productId);
}
