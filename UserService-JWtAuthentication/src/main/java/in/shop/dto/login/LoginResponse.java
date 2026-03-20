package in.shop.dto.login;

import java.time.LocalDateTime;

public record LoginResponse(

        LocalDateTime currentDateAndTime,
        String message,
        String token
) {
}
