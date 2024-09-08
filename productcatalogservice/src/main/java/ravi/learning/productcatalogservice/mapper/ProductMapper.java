package ravi.learning.productcatalogservice.mapper;

import org.springframework.stereotype.Component;
import ravi.learning.productcatalogservice.dto.ProductDto;
import ravi.learning.productcatalogservice.models.Category;
import ravi.learning.productcatalogservice.models.Product;
@Component
public class ProductMapper {
    public ProductDto toProductDtoMapper(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setId(product.getId());
        productDto.setImage(product.getImageUrl());
        productDto.setCategory(product.getCategory().getName());

        return productDto;
    }

    public Product toProductMapper(ProductDto productDto) {
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
