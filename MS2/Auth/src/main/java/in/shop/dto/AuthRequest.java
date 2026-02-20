package in.shop.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record AuthRequest(String firstName,String phoneNumber ,String lastName, String dateOfBirth, LocalDateTime createdAt, LocalDateTime updatedAt, String username, String password,
                          Set<String> role) {
}
