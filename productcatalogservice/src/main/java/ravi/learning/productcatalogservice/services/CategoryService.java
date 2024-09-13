package ravi.learning.productcatalogservice.services;

import ravi.learning.productcatalogservice.models.Category;
import ravi.learning.productcatalogservice.models.Product;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public List<Product> getAllProductsInCategory(String productName);
}
