package uz.bob.pcmarket_with_security.payload;

import lombok.Data;

@Data
public class CategoryDto {

    private String name;

    private Integer parentCategoryId;

}
