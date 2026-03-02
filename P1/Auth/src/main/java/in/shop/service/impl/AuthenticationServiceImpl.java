package in.shop.service.impl;

import in.shop.dto.LoginDto;
import in.shop.dto.LoginResponseDto;
import in.shop.dto.UserRegisterDto;
import in.shop.dto.UserResponseDto;
import in.shop.entity.AuthUser;
import in.shop.exception.UserExistException;
import in.shop.mapper.DtoMapper;
import in.shop.repository.UserRepository;
import in.shop.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements UserAuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public String registerUserAsAdmin(UserRegisterDto request) {
        log.info("Registering User as Admin{}",request.username());

        AuthUser authUser = DtoMapper.mapToUser(request);
        if(userRepository.findByUsername(authUser.getUsername()).isPresent()){
            throw new UserExistException("User already exists fot the Username :"+request.username());
        }
    authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
    authUser.setRoles(List.of("ADMIN","USER"));

        AuthUser saved = userRepository.save(authUser);

        return "User Save in DataBase with foe the User : "+saved.getUsername();
    }

    @Override
    public String registerUserAsUser(UserRegisterDto request) {
        log.info("Registering User as User{}",request.username());

        AuthUser authUser = DtoMapper.mapToUser(request);
        if(userRepository.findByUsername(authUser.getUsername()).isPresent()){
            throw new UserExistException("User already exists fot the Username :"+request.username());
        }
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUser.setRoles(List.of("USER"));
        AuthUser saved = userRepository.save(authUser);

        return "User Save in DataBase with foe the User : "+saved.getUsername();
    }

    @Override
    public LoginResponseDto login(LoginDto request) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if (!authenticate.isAuthenticated()) {
            throw new BadCredentialsException("Bad credentials");
        }
        AuthUser user = (AuthUser) authenticate.getPrincipal();
        assert user != null;
        return new LoginResponseDto(
                LocalDateTime.now(),
                "HI User Mr : "+user.getFirstName() +" "+user.getLastName()+" Welcome User Service You Are Authenticated",
                user.getUserId(),
                user.getUsername(),
                "Here Token"

        );
    }
}
