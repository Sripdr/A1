package in.shop.mapper;

import in.shop.dto.AuthResponse;
import in.shop.dto.UserRegisterRequest;
import in.shop.entity.AuthUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class DTOMapper {

    public static AuthUser mapToAuthUser(UserRegisterRequest request){

        AuthUser authUser = new AuthUser();
        authUser.setUserFirstName(request.firstName().toUpperCase());
        authUser.setUserLastName(request.lastName().toUpperCase());
        authUser.setUserPhoneNumber("+91"+request.phoneNumber());
        authUser.setUserDateOfBirth(LocalDate.parse(request.dateOfBirth()));
        authUser.setUserCreatedDate(LocalDateTime.now());
        authUser.setUsername(request.username().toUpperCase());
        authUser.setPassword(request.password());
        return authUser;
    }

    public static AuthResponse mapToResponse(AuthUser authUser){

        return new AuthResponse(
                authUser.getUserId(),
                authUser.getUserFirstName(),
                authUser.getUserLastName(),
                authUser.getUserPhoneNumber(),
                authUser.getUserDateOfBirth(),
                authUser.getUserCreatedDate(),
                authUser.getUserUpdatedDate(),
                authUser.getUsername(),
                authUser.getUserRoles().stream().map(role->"ROLE_"+role).toList()
        );
    }
}
