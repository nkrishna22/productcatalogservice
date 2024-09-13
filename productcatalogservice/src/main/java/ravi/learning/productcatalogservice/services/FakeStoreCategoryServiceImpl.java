package ravi.learning.productcatalogservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ravi.learning.productcatalogservice.clients.FakeStoreClient;
import ravi.learning.productcatalogservice.dto.CategoryDto;
import ravi.learning.productcatalogservice.dto.FakeStoreCategoryDto;
import ravi.learning.productcatalogservice.dto.ProductDto;
import ravi.learning.productcatalogservice.models.Category;
import ravi.learning.productcatalogservice.models.Product;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreCategoryServiceImpl implements CategoryService{

    private FakeStoreClient fakeStoreClient;
    private ModelMapper modelMapper;

    public FakeStoreCategoryServiceImpl(FakeStoreClient fakeStoreClient, ModelMapper modelMapper) {
        this.fakeStoreClient = fakeStoreClient;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<Category> getAllCategories() {
        List<String> categoryDtoList = fakeStoreClient.getAllCategories();
        List<Category> categories = new ArrayList<>();
        for(String categoryDto : categoryDtoList) {
            Category category = new Category();
            category.setName(categoryDto);
            categories.add(category);
        }
        return categories;
    }

    @Override
    public List<Product> getAllProductsInCategory(String productName) {
        List<ProductDto> productDtoList = fakeStoreClient.getAllProductsInCategory(productName);
        List<Product> products = new ArrayList<>();
        for(ProductDto productDto : productDtoList) {
            products.add(modelMapper.map(productDto, Product.class));
        }
        return products;
    }
}
