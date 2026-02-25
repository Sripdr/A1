package in.shop.dto;

import java.time.LocalDateTime;

public record AuthResponse(Long userId, String firstName, String lastName, String phoneNumber, String dateOfBirth,
                           LocalDateTime createdAt,LocalDateTime updatedAt, String username, String[] roles) {
}
