package in.shop.dto.user;

import java.util.List;

public record AuthResponse(
        String authId,
        String email,
        String phoneNumber,
        List<String> roles,
        InfoResponse userInfo
) {
}
