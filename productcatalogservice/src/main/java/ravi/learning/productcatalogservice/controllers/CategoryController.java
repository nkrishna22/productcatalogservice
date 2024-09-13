package ravi.learning.productcatalogservice.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ravi.learning.productcatalogservice.dto.CategoryDto;
import ravi.learning.productcatalogservice.dto.ProductDto;
import ravi.learning.productcatalogservice.models.Category;
import ravi.learning.productcatalogservice.models.Product;
import ravi.learning.productcatalogservice.services.CategoryService;
import ravi.learning.productcatalogservice.services.FakeStoreCategoryServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {
    private CategoryService fakeStoreCategoryService;
    private ModelMapper mapper;
    public CategoryController(CategoryService fakeStoreCategoryService, ModelMapper mapper) {
        this.fakeStoreCategoryService = fakeStoreCategoryService;
        this.mapper = mapper;
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<Category> categories = fakeStoreCategoryService.getAllCategories();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for(Category category : categories) {
            categoryDtoList.add(mapper.map(category, CategoryDto.class));
        }
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<List<ProductDto>> getAllProductsInCategory(@PathVariable(value = "categoryName") String categoryName) {
        List<Product> productsList = fakeStoreCategoryService.getAllProductsInCategory(categoryName);
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productsList) {
            productDtoList.add(mapper.map(product, ProductDto.class));
        }
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }
}
