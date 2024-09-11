package ravi.learning.productcatalogservice.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ravi.learning.productcatalogservice.dto.ProductDto;
import ravi.learning.productcatalogservice.exceptions.NotFoundException;
import ravi.learning.productcatalogservice.models.Category;
import ravi.learning.productcatalogservice.models.Product;
import ravi.learning.productcatalogservice.services.FakeStoreProductServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private FakeStoreProductServiceImpl fakeStoreProductService;
    private ModelMapper mapper;

    public ProductController(FakeStoreProductServiceImpl fakeStoreProductService, ModelMapper mapper) {
        this.fakeStoreProductService = fakeStoreProductService;
        this.mapper = mapper;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = fakeStoreProductService.getAllProducts();
        List<ProductDto> answers = new ArrayList<>();
        for(Product pros :products) {
            ProductDto productDto = productDtoMapper(pros);
            answers.add(productDto);
        }
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Optional<Product>> getSingleProduct(@PathVariable(value = "productId") Long productId) throws NotFoundException{
        Optional<Product> productOptional = fakeStoreProductService.getSingleProduct(productId);
        if(productOptional.isEmpty()) {
            throw new NotFoundException("No product with product id: " + productId);
        }


        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add(
                "auth-token","noaccess4uheyhey"
        );
        return new ResponseEntity<>(productOptional, headers, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto productDto) {
        Product product = fakeStoreProductService.addNewProduct(productDto);

        return new ResponseEntity<>(mapper.map(product, ProductDto.class), HttpStatus.CREATED); // using ModelMapper module to map Product to ProductDto
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(value = "productId") Long productId, @RequestBody ProductDto productDto) {
        Product product = fakeStoreProductService.updateProduct(productId, productDto);
        ProductDto productDto1 = mapper.map(product, ProductDto.class);         // using ModelMapper to map Product to ProductDto
        return new ResponseEntity<>(productDto1, HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable(value = "productId") Long productId) throws NotFoundException {
        Optional<Product> productOptional = fakeStoreProductService.deleteProduct(productId);
        if(productOptional.isEmpty()) {
            throw new NotFoundException("No product with product id: " + productId);
        }
        return new ResponseEntity<>(mapper.map(productOptional, ProductDto.class), HttpStatus.OK);      // using ModdelMapper to map Product with ProductDto
    }

    public ProductDto productDtoMapper(Product product1) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product1.getId());
        productDto.setTitle(product1.getTitle());
        productDto.setPrice(product1.getPrice());
        productDto.setDescription(product1.getDescription());
        productDto.setImage(product1.getImageUrl());
        productDto.setCategory(product1.getCategory().getName());

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
