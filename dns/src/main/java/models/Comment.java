package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
    private Long id;
    private Long productId;
    private String userName;
    private String text;
}
