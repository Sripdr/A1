package in.shop.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime time, String message) {
}
