package in.shop.dto;

import java.time.LocalDateTime;

public record LoginResponse(String message, LocalDateTime DateAndTimeIs, String yourUsername, String token) {
}
