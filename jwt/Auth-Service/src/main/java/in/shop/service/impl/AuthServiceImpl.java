package in.shop.service.impl;

import in.shop.dto.login.LoginRequest;
import in.shop.dto.login.LoginResponse;
import in.shop.dto.user.AuthRequest;
import in.shop.dto.user.AuthResponse;
import in.shop.entity.AuthUser;
import in.shop.jwt.JwtService;
import in.shop.map.RequestAndResponseMapper;
import in.shop.repository.AuthRepository;
import in.shop.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.Set;

import static in.shop.util.AuthConstants.ROLE_ADMIN;
import static in.shop.util.AuthConstants.ROLE_USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthRepository repository;
    private final RequestAndResponseMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager manager;
    private final JwtService jwtService;


    @Override
    public AuthResponse admin(AuthRequest request) {
        log.info("Registering User for admin role");

        AuthUser authUser = mapper.toAuthUser(request);
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUser.setRoles(Set.of(ROLE_ADMIN,ROLE_USER));
        if(authUser.getUserInfo()!=null)
            authUser.getUserInfo().setFirstName(authUser.getUserInfo().getFirstName().toUpperCase());
            authUser.getUserInfo().setLastName(authUser.getUserInfo().getLastName().toUpperCase());
            authUser.getUserInfo().setGender(authUser.getUserInfo().getGender().toUpperCase());

            authUser.getUserInfo().setAuthUser(authUser);

        AuthUser saved = repository.save(authUser);
        log.info("User registered successfully with id: {}", saved.getAuthId());


        return new AuthResponse(
                saved.getAuthId(),
                saved.getEmail(),
                saved.getPhoneNumber(),
                saved.getRoles().stream().map(r->"ROLES_"+r).toList(),
                mapper.toInfoResponse(saved.getUserInfo())
        );
    }

    @Override
    public AuthResponse user(AuthRequest request) {

        log.info("Registering User for admin role");

        AuthUser authUser = mapper.toAuthUser(request);
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUser.setRoles(Set.of(ROLE_USER));
        if(authUser.getUserInfo()!=null)

            authUser.getUserInfo().setFirstName(authUser.getUserInfo().getFirstName().toUpperCase());
        authUser.getUserInfo().setLastName(authUser.getUserInfo().getLastName().toUpperCase());
        authUser.getUserInfo().setGender(authUser.getUserInfo().getGender().toUpperCase());

        authUser.getUserInfo().setAuthUser(authUser);

        AuthUser saved = repository.save(authUser);
        log.info("User registered successfully with id: {}", saved.getAuthId());


        return new AuthResponse(
                saved.getAuthId(),
                saved.getEmail(),
                saved.getPhoneNumber(),
                saved.getRoles().stream().map(r->"ROLES_"+r).toList(),
                mapper.toInfoResponse(saved.getUserInfo())
        );
    }

/**
 * Overrides the default login method to handle user authentication and token generation.
 *
 * @param request The login request containing username and password
 * @return LoginResponse containing authentication message and JWT token
 * @throws UserPrincipalNotFoundException If authentication fails due to invalid credentials
 */
    @Override
    //  login method
    public LoginResponse login(LoginRequest request) throws UserPrincipalNotFoundException {
    // Log the login request for debugging and monitoring purposes

        log.info("login request received");
        Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if (!authenticate.isAuthenticated())
            throw new UserPrincipalNotFoundException("Invalid credentials");
        AuthUser user = (AuthUser) authenticate.getPrincipal();
        assert user != null;
        //generate token
        String token = jwtService.generateToken(user);

        return new LoginResponse(
                LocalDateTime.now(),
                "HI User Mr: "+user.getUserInfo().getFirstName() +" "+user.getUserInfo().getLastName()+" Welcome to SHOP.IN ,You Are Authenticated",
                token
        );


    }
}
