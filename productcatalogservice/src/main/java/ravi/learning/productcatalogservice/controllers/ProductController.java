package ravi.learning.productcatalogservice.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import ravi.learning.productcatalogservice.dto.ProductDto;

@RestController
@RequestMapping("https://fakestoreapi.com/products")
public class ProductController {

    public String getAllProducts() {
        return "Getting all products";
    }

    public String getSingleProduct() {
        return "Returning single product";
    }

    @GetMapping("/products/{productId}")
    public String updateSingleProduct(@PathVariable(value = "productId") Long productId) {
        return "Updated existing product";
    }
    @PostMapping("/products")
    public String addNewProduct(@RequestBody ProductDto productDto) {
        return "Adding new product!";
    }

    @PutMapping("/products/{productId}")
    public String updateProduct(@PathVariable(value = "productId") Long productId, @RequestBody ProductDto productDto) {
        return "Product updated";
    }

    @DeleteMapping("/products/{productId}")
    public String DeleteProduct(@PathVariable(value = "ProductId") Long productId) {
        return "Deleting Product";
    }
}
