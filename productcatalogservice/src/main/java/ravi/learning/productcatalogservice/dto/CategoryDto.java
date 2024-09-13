package ravi.learning.productcatalogservice.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto {
    private String name;
    private String description;
    private List<ProductDto> productDtoList;
}
