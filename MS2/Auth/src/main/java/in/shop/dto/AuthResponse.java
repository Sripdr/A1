package in.shop.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record AuthResponse(Long userId,String firstName, String lastName,String phoneNumber, String dateOfBirth, LocalDateTime createdAt, LocalDateTime updatedAt, String username,
                           Set<String> role) {
}
