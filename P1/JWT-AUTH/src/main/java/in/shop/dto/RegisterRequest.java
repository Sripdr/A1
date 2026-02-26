package in.shop.dto;

public record RegisterRequest(String firstName, String lastName, String phoneNumber, String dateOfBirth, String username,
                              String password) {

}
