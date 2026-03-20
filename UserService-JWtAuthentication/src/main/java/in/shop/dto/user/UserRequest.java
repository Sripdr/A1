package in.shop.dto.user;

public record UserRequest(

        String email,
        Long phoneNumber,
        String password,
        InfoRequest userInfo
) {
}
