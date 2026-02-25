package in.shop.map;

import in.shop.dto.AuthRequest;
import in.shop.dto.AuthResponse;
import in.shop.entity.AuthUser;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@Builder
public class DTOMapper {

    public static AuthUser mapToAuthUser(AuthRequest authRequest) {
        AuthUser authUser = new AuthUser();
        authUser.setFirstName(authRequest.firstName());
        authUser.setLastName(authRequest.lastName());
        authUser.setPhoneNumber("+91"+authRequest.phoneNumber());
        authUser.setDateOfBirth(LocalDate.parse(authRequest.dateOfBirth()));
        authUser.setUsername(authRequest.username());
        authUser.setPassword(authRequest.password());
        authUser.setRoles(Set.of(authRequest.roles()));
        return authUser;
    }

        public static AuthResponse mapToAuthResponse(AuthUser authUser) {
            return new AuthResponse(
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
