package in.shop.dto.user;

import java.util.List;

public record UserResponse(
        Long id,
        String authId,
        String email,
        String phoneNumber,
        List<String> roles,
        InfoResponse userInfo
) {
}
