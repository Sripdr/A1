package in.shop.dto;

import jakarta.validation.constraints.Size;

public record RegisterRequest(String firstName, String lastName, @Size( max = 10, message = "Phone Number must  10 digits") String phoneNumber, String dateOfBirth, String username,
                              String password) {

}
