package ravi.learning.productcatalogservice.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ravi.learning.productcatalogservice.dto.ProductDto;
import ravi.learning.productcatalogservice.models.Category;
import ravi.learning.productcatalogservice.models.Product;
import ravi.learning.productcatalogservice.services.FakeStoreProductServiceImpl;

@RestController
public class ProductController {

    private FakeStoreProductServiceImpl fakeStoreProductService;

    public ProductController(FakeStoreProductServiceImpl fakeStoreProductService) {
        this.fakeStoreProductService = fakeStoreProductService;
    }

    public String getAllProducts() {
        return "Getting all products";
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable(value = "productId") Long productId) {
        Product product = fakeStoreProductService.getSingleProduct(productId);
        ProductDto productDto = productDtoMapper(product);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add(
                "auth-token","noaccess4uheyhey"
        );
        return new ResponseEntity<>(productDto, headers, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper(productDto);
        ProductDto productDto1 = fakeStoreProductService.addNewProduct(product);

        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    @PutMapping("/products/{productId}")
    public String updateProduct(@PathVariable(value = "productId") Long productId, @RequestBody ProductDto productDto) {
        return "Product updated";
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable(value = "ProductId") Long productId) {
        return "Deleting Product";
    }

    public ProductDto productDtoMapper(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImageUrl());
        productDto.setCategory(product.getCategory().getName());

        return productDto;
    }

    public Product productMapper(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
