package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProduct {
    private Long id;
    private Long userId;
    private Long productId;
    private boolean isProcessed;
}
