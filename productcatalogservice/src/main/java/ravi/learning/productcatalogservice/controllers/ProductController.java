package ravi.learning.productcatalogservice.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    public String getAllProducts() {
        return "Getting all products";
    }

    public String getSingleProduct() {
        return "Returning single product";
    }

    public String updateSingleProduct() {
        return "Updated existing product";
    }

    public String addNewProduct() {
        return "Adding new product!";
    }

    public String DeleteProduct() {
        return "Deleting Product";
    }
}
