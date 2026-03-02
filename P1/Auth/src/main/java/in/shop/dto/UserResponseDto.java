package in.shop.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record UserResponseDto(
        Long userId,

        String firstName,

        String lastName,

        String phoneNumber,

        LocalDate dateOfBirth,

        LocalDateTime createdDate,

        LocalDateTime updatedDate,

        String username,

       List<String> roles
) {
}
