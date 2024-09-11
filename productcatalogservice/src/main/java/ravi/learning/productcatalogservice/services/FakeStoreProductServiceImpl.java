package ravi.learning.productcatalogservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ravi.learning.productcatalogservice.clients.FakeStoreClient;
import ravi.learning.productcatalogservice.dto.ProductDto;
import ravi.learning.productcatalogservice.exceptions.NotFoundException;
import ravi.learning.productcatalogservice.mapper.ProductMapper;
import ravi.learning.productcatalogservice.models.Category;
import ravi.learning.productcatalogservice.models.Product;

import java.util.*;

@Service
public class FakeStoreProductServiceImpl implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    private ProductMapper productMapper1;
    private ModelMapper mapper;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, ProductMapper productMapper1, FakeStoreClient fakeStoreClient, ModelMapper mapper) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productMapper1 = productMapper1;
        this.fakeStoreClient = fakeStoreClient;
        this.mapper = mapper;
    }
    @Override
    public List<Product> getAllProducts() {
        List<ProductDto> productDtoList = fakeStoreClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        for(ProductDto productDto : productDtoList) {
            products.add(mapper.map(productDto, Product.class));        // using ModelMapper module for mapping ProductDto with Product
        }
        return products;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        Optional<ProductDto> productDto1= fakeStoreClient.getSingleProduct(productId);
        return Optional.ofNullable(mapper.map(productDto1, Product.class));     // Using ModelMapper module for mapping ProductDto with Product
    }

    @Override
    public Product addNewProduct(ProductDto product) {
        ProductDto productDto = fakeStoreClient.addNewProduct(product);

        return mapper.map(productDto, Product.class);
    }

    @Override
    public Product updateProduct(Long productId, ProductDto product) {
        ProductDto productDto = fakeStoreClient.updateProduct(productId, product);
        return mapper.map(productDto, Product.class);
    }

    @Override
    public Optional<Product> deleteProduct(Long productId) throws NotFoundException {
        Optional<ProductDto> productDto = fakeStoreClient.deleteProduct(productId);

       return Optional.ofNullable(mapper.map(productDto, Product.class));
    }
}
