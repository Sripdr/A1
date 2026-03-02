package in.shop.dto;

import java.time.LocalDateTime;

public record LoginResponseDto(
        LocalDateTime currentDateAndTime,
        String message,
        Long yourUserId,
        String yourUsername,
        String jwtToken
) {
}
