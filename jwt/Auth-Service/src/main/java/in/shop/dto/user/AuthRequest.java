package in.shop.dto.user;

public record AuthRequest(

        String email,
        Long phoneNumber,
        String password,
        InfoRequest userInfo
) {
}
