package uz.bob.pcmarket_with_security.payload;

import lombok.Data;

@Data
public class ProductDto {

    private String name;

    private Integer categoryId;

    private Double price;

    private Integer photoId;

    private Integer characteristicId;
}
