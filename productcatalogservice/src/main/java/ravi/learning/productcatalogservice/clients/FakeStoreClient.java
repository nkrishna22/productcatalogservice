package ravi.learning.productcatalogservice.clients;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ravi.learning.productcatalogservice.dto.CategoryDto;
import ravi.learning.productcatalogservice.dto.FakeStoreCategoryDto;
import ravi.learning.productcatalogservice.dto.ProductDto;
import ravi.learning.productcatalogservice.exceptions.NotFoundException;
import ravi.learning.productcatalogservice.models.Category;
import ravi.learning.productcatalogservice.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
     public List<ProductDto> getAllProducts() {
         RestTemplate restTemplate = restTemplateBuilder.build();
         ResponseEntity<ProductDto[]> responses = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDto[].class);
         return Arrays.asList(responses.getBody());
     }

     public Optional<ProductDto> getSingleProduct(Long productId) throws NotFoundException {
         RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, productId);
//        (url, return type, parameters in url... should be mentioned)
         ProductDto productDto = response.getBody();
         if(productDto ==null) {
             return Optional.empty();
         }
         return Optional.of(productDto);
     }

     public ProductDto addNewProduct(ProductDto productDto) {
         RestTemplate restTemplate = restTemplateBuilder.build();
         ResponseEntity<ProductDto> response = restTemplate.postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class);
         ProductDto productDto1 = response.getBody();

        return productDto1;
     }

     public ProductDto updateProduct(long productId, ProductDto productDto) {
         ResponseEntity<ProductDto> response = requestForEntity(HttpMethod.PUT, "https://fakestoreapi.com/products/{id}", productDto, ProductDto.class, productId);
         return response.getBody();
     }

     public Optional<ProductDto> deleteProduct(Long productId) throws NotFoundException{
        RestTemplate restTemplate = new RestTemplate();
        Optional<ProductDto> productDto = getSingleProduct(productId);
        if(productDto.isEmpty()) {
            return Optional.empty();
        }

        // using customized function "requestForEntity for Delete API

        ResponseEntity<ProductDto> response = requestForEntity(HttpMethod.DELETE,"https://fakestoreapi.com/products/{productId}", productDto, ProductDto.class, productId);
        return Optional.ofNullable(response.getBody());

     }

    public List<String> getAllCategories() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String[]> responses = restTemplate.getForEntity("https://fakestoreapi.com/products/categories", String[].class);
        return Arrays.asList(responses.getBody());
    }

    public List<ProductDto> getAllProductsInCategory(String categoryName) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto[]> responses = restTemplate.getForEntity("https://fakestoreapi.com/products/category/{categoryName}", ProductDto[].class, categoryName);
        return Arrays.asList(responses.getBody());
    }

    // customized function to update API to update the product
    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
