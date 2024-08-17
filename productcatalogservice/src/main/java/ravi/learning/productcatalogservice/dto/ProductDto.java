package ravi.learning.productcatalogservice.dto;

import lombok.*;
import ravi.learning.productcatalogservice.models.Category;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    private String title;
    private double price;
    private String description;
    private Category category;
    private String image;
}
