package in.shop.exception;

import java.time.LocalDateTime;

public record ErrorDetails(
        LocalDateTime timestamp, String path, String message
) {
}
