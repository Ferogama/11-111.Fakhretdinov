package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private byte[] img;
    private String imgName;
}
