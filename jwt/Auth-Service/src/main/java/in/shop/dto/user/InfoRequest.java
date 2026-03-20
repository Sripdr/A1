package in.shop.dto.user;

public record InfoRequest(
        String firstName,
        String lastName,
        String gender,
        String dateOfBirth
) {
}
