package in.shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegisterDto(

        @NotNull(message = "Please Enter First Name")
         String firstName,

        @NotNull(message = "Please Enter Last Name")
         String lastName,

        @NotNull(message = "Please Enter valid phone number")
        @Size(min = 10,max = 10)
         String phoneNumber,

        @NotNull(message = "Please Enter valid Date of Birth")
         String dateOfBirth,

        @NotNull(message = "Please Enter Valid Username")
         String username,

         @NotNull(message = "Please Enter valid password format")
         String password


) {
}
