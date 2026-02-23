package in.shop.dto;

public record AuthReq(String firstName, String lastName, String phoneNumber, String dateOfBirth, String username, String password, String[] roles) {
}
