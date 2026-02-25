package in.shop.serv.impl;

import in.shop.dto.AuthRequest;
import in.shop.dto.AuthResponse;
import in.shop.dto.LoginRequest;
import in.shop.dto.LoginResponse;
import in.shop.entity.AuthUser;
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
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(AuthRequest request) {

        AuthUser authUser = DTOMapper.mapToAuthUser(request);
        log.info("Registering user: {}", authUser.getUsername());

        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUser.setCreatedAt(LocalDateTime.now());

        AuthUser saved = authRepository.save(authUser);
        log.info("User Registered successfully for User "+saved.getUsername());
        return DTOMapper.mapToAuthResponse(saved);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        log.info("The Rrequest for login for User :"+request.username());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        if (!authenticate.isAuthenticated())
            throw new UsernameNotFoundException("Invalid username or password");
        AuthUser user = (AuthUser) authenticate.getPrincipal();
        assert user != null;
        return new LoginResponse("Hi User You are Authenticate for user ID :"+user.getUserId()+ " \n,User Details"+user.toString(),
                "Current Date & Time Is : "+LocalDateTime.now(),
                "The User Is "+user.getUsername().toUpperCase(),
                " here token is "
        );

    }

}
