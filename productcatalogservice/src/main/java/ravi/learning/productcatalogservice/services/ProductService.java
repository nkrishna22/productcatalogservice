package ravi.learning.productcatalogservice.services;

import ravi.learning.productcatalogservice.dto.ProductDto;
import ravi.learning.productcatalogservice.exceptions.NotFoundException;
import ravi.learning.productcatalogservice.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAllProducts();

    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException;

    public Product addNewProduct(ProductDto product);

    public Product  updateProduct(Long productId, ProductDto product);
    public Optional<Product> deleteProduct(Long productId) throws NotFoundException;
}
