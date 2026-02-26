package in.shop.dto;

import java.time.LocalDateTime;

public record AuthUserResponse(Long userId, String firstName, String lastName, String phoneNumber, String dateOfBirth,
                               LocalDateTime createdAt, LocalDateTime updatedAt, String username, String[] roles) {
}
