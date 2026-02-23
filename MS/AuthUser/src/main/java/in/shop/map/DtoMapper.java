package in.shop.map;

import in.shop.dto.AuthReq;
import in.shop.dto.AuthRes;
import in.shop.entity.AuthUser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class DtoMapper {

    public static AuthUser mapToAuthUser(AuthReq authReq) {
        AuthUser authUser = new AuthUser();
        authUser.setFirstName(authReq.firstName());
        authUser.setLastName(authReq.lastName());
        authUser.setPhoneNumber(authReq.phoneNumber());
        authUser.setDateOfBirth(LocalDate.parse(authReq.dateOfBirth()));
        authUser.setUsername(authReq.username());
        authUser.setPassword(authReq.password());
        authUser.setRoles(Set.of(authReq.roles()));
        return authUser;
    }

    public static AuthRes mapToAuthRes(AuthUser authUser) {
        return new AuthRes(
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
