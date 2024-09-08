package ravi.learning.productcatalogservice.services;

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
import ravi.learning.productcatalogservice.dto.ProductDto;
import ravi.learning.productcatalogservice.mapper.ProductMapper;
import ravi.learning.productcatalogservice.models.Category;
import ravi.learning.productcatalogservice.models.Product;

import java.util.*;

@Service
public class FakeStoreProductServiceImpl implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private ProductMapper productMapper1;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, ProductMapper productMapper1) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productMapper1 = productMapper1;
    }
    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto[]> responses = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDto[].class);
        List<Product> products = new ArrayList<>();

        for(ProductDto productDto : responses.getBody()) {
            Product product = productMapper1.toProductMapper(productDto);
            products.add(product);
        }

        return products;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, productId);
//        (url, return type, parameters in url... should be mentioned)
        ProductDto productDto = response.getBody();
        if(productDto ==null) {
            return Optional.empty();
        }
        return Optional.of(productMapper1.toProductMapper(productDto));
    }

    @Override
    public ProductDto addNewProduct(ProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.postForEntity("https://fakestoreapi.com/products", product, ProductDto.class);
        ProductDto productDto = response.getBody();
        return productDto;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto product) {
        /*RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.("https://fakestoreapi.com/products/{id}", product, ProductDto.class);*/
        ResponseEntity<ProductDto> response = requestForEntity(HttpMethod.PUT, "https://fakestoreapi.com/products/{id}", product, ProductDto.class, productId);
        return response.getBody();
    }

    @Override
    public ProductDto deleteProduct(Long productId) {
       return null;
    }
}
