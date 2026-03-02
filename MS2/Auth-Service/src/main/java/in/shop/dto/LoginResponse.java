package in.shop.dto;

import java.time.LocalDateTime;

public record LoginResponse(
        LocalDateTime localDateAndTime,
        Long userId,
        String message,
        String jWtToken
) {
}
