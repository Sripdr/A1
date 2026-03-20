package in.shop.dto.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record InfoResponse(
        Long id,
        String firstName,
        String lastName,
        String gender,
        LocalDate dateOfBirth,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
