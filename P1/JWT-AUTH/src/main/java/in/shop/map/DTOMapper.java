package in.shop.map;

import in.shop.dto.RegisterRequest;
import in.shop.dto.AuthUserResponse;
import in.shop.entity.AuthUser;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Builder
public class DTOMapper {

    public static AuthUser mapToAuthUser(RegisterRequest authRequest) {
        AuthUser authUser = new AuthUser();
        authUser.setFirstName(authRequest.firstName().toUpperCase());
        authUser.setLastName(authRequest.lastName().toUpperCase());
        authUser.setPhoneNumber("+91"+authRequest.phoneNumber());
        authUser.setDateOfBirth(LocalDate.parse(authRequest.dateOfBirth()));
        authUser.setCreatedAt(LocalDateTime.now());
        authUser.setUsername(authRequest.username().toUpperCase());
        authUser.setPassword(authRequest.password());

        return authUser;
    }



        public static AuthUserResponse mapToAuthResponse(AuthUser authUser) {
            return new AuthUserResponse(
                    authUser.getUserId(),
                    authUser.getFirstName(),
                    authUser.getLastName(),
                    authUser.getPhoneNumber(),
                    authUser.getDateOfBirth().toString(),
                    authUser.getCreatedAt(),
                    authUser.getUpdatedAt(),
                    authUser.getUsername(),
                    authUser.getRoles().toArray(new String[0])
            );
        }


}
