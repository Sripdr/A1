package in.shop.dto;

import java.time.LocalDateTime;

public record AuthRequest(String firstName, String lastName, String phoneNumber, String dateOfBirth, String username,
                          LocalDateTime createdAt,LocalDateTime updatedAt, String password, String[] roles) {
}
