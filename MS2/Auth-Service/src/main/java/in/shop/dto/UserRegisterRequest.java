package in.shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UserRegisterRequest(
        @NotNull(message = "Please Enter First Name")
        String firstName,
        @NotNull(message = "Please Enter Last Name")
        String lastName,
        @NotNull(message = "Please Enter Phone Number")
        @Length(min = 10, max = 10)
        String phoneNumber,
        @NotNull(message = "Please Enter Date Of Birth")
        String dateOfBirth,
        @Email(message = "Username must be a valid email address", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        String username,
        @NotNull(message = "Please Enter Valid Password ")
        String password

) {
}
