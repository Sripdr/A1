package in.shop.mapper;

import in.shop.dto.UserRegisterDto;
import in.shop.dto.UserResponseDto;
import in.shop.entity.AuthUser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class DtoMapper {

    public static AuthUser mapToUser(UserRegisterDto user){
        AuthUser authUser = new AuthUser();

        authUser.setFirstName(user.firstName().toUpperCase());
        authUser.setLastName(user.lastName().toUpperCase());
        authUser.setPhoneNumber("+91"+user.phoneNumber());
        authUser.setDateOfBirth(LocalDate.parse(user.dateOfBirth()));
        authUser.setCreatedDate(LocalDateTime.now());
        authUser.setUsername(user.username().toUpperCase());
        authUser.setPassword(user.password());
        return authUser;
    }

    public static UserResponseDto mapToDto(AuthUser user){

        return new UserResponseDto(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getDateOfBirth(),
                user.getCreatedDate(),
                user.getUpdatedDate(),
                user.getUsername(),
                user.getRoles().stream().map(rol->"ROLE_"+rol).collect(Collectors.toList())
                );


    }

}
