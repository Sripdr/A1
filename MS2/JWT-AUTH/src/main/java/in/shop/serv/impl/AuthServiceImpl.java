package in.shop.serv.impl;

import in.shop.dto.*;
import in.shop.entity.AuthUser;
import in.shop.jwt.JWTService;
import in.shop.map.DTOMapper;
import in.shop.repo.AuthRepository;
import in.shop.serv.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public RegisterResponse registerAdmin(RegisterRequest request) {

        AuthUser authUser = DTOMapper.mapToAuthUser(request);
        log.info("Registering user: {}", authUser.getUsername());
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUser.setRoles(Set.of("ADMIN","USER"));
        AuthUser saved = authRepository.save(authUser);
        log.info("User Registered successfully for User "+saved.getUsername(),authUser.getUserId(),authUser.getCreatedAt());
        return new RegisterResponse("  User Registered Successfully ," +
                "\n Welcome to User Auth Service \n Current Time Is : "+LocalDateTime.now()
                ,authUser.getUserId(), authUser.getUsername());
    }

    @Override
    public RegisterResponse registerUser(RegisterRequest request) {
        log.info("The Request for registration for User :"+request.username());
            AuthUser authUser = DTOMapper.mapToAuthUser(request);
            authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
             authUser.setRoles(Collections.singleton("USER"));
            AuthUser saved = authRepository.save(authUser);
            log.info("User Registered successfully for User "+saved.getUsername(),authUser.getUserId(),authUser.getCreatedAt());
             return new RegisterResponse(" Welcome to User Auth Service,"+ "\n" +"Current Time Is : "+LocalDateTime.now()+
                 "  User Registered Successfully "
                ,authUser.getUserId(), " Username Is :"+authUser.getUsername());
    }

    @Override
    public LoginResponse login(LoginRequest request) throws UnsupportedEncodingException {

        log.info("The Request for login for User :"+request.username().toUpperCase());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        if (!authenticate.isAuthenticated())
            throw new UsernameNotFoundException("Invalid username or password"+request.username());
        AuthUser user = (AuthUser) authenticate.getPrincipal();

        assert user != null;
      String JWTToken =  jwtService.generateToken(user.getUsername());


        return new LoginResponse("Hi  "+user.getFirstName()+ " "+user.getLastName()+" Welcome To SHOP.IN  You are Authenticated, Your User ID is : "+user.getUserId(),
                LocalDateTime.now(),
                user.getUsername(),
                JWTToken
        );

    }

}
