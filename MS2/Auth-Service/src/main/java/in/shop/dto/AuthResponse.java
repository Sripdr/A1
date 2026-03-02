package in.shop.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AuthResponse(
        Long userId,
        String firstNme,
        String lastName,
        String phoneNumber,
        LocalDate dateOfBirth,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        String username,
        List<String> roles


) {
}
