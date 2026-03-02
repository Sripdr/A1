package in.shop.service.impl;

import in.shop.dto.LoginRequest;
import in.shop.dto.LoginResponse;
import in.shop.dto.UserRegisterRequest;
import in.shop.entity.AuthUser;
import in.shop.jwtutil.JWTService;
import in.shop.mapper.DTOMapper;
import in.shop.repository.UserAuthRepository;
import in.shop.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthenticationService {

    private final UserAuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public String registerUserAsAdmin(UserRegisterRequest request) {
        log.info("registerUserAsAdmin for{} ",request.username());
        AuthUser authUser = DTOMapper.mapToAuthUser(request);

        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUser.setUserRoles(Set.of("ADMIN","USER"));
        AuthUser saved = authRepository.save(authUser);
        return "User Successfully Saved In Database for the User : " +saved.getUsername();
    }

    @Override
    public String registerUserAsUser(UserRegisterRequest request) {
        log.info("registerUserAsAdmin for{} ",request.username());
        AuthUser authUser = DTOMapper.mapToAuthUser(request);

        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUser.setUserRoles(Set.of("USER"));
        AuthUser saved = authRepository.save(authUser);
        return "User Successfully Saved In Database for the User : " +saved.getUsername();

    }

    @Override
    public LoginResponse login(LoginRequest request) {
        log.info("login for{} ",request.username());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if (!authenticate.isAuthenticated())
             throw new RuntimeException("Incorrect username or password");
        AuthUser user = (AuthUser) authenticate.getPrincipal();
        assert user != null;
        String token = jwtService.generateToken(user.getUsername());
        return new LoginResponse(
                LocalDateTime.now(),
                user.getUserId(),
                "HI User Mr. " +user.getUserFirstName() +" "+user.getUserLastName()+
                            " You Are Authenticated Your Username Is : "+user.getUsername(),
                token);
    }
}
