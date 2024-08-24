package ravi.learning.productcatalogservice.dto;

import lombok.*;
import ravi.learning.productcatalogservice.models.Category;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
