package in.shop.service.impl;

import in.shop.dto.login.LoginRequest;
import in.shop.dto.login.LoginResponse;
import in.shop.dto.user.UserRequest;
import in.shop.dto.user.UserResponse;
import in.shop.entity.Users;
import in.shop.exception.UserExistException;
import in.shop.jwt.JWTService;
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

import static in.shop.util.AuthConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthRepository repository;
    private final RequestAndResponseMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager manager;
    private final JWTService jwtService;

    @Override
    public UserResponse admin(UserRequest request) {
        log.info("Registering User for admin role");

        Users users = mapper.toAuthUser(request);
        if (repository.findByEmail(users.getEmail()).isPresent())
            throw new UserExistException(EXIST+users.getEmail());
        if (repository.findByPhoneNumber(users.getPhoneNumber()).isPresent())
            throw new UserExistException(EXIST+users.getPhoneNumber());
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRoles(Set.of(ROLE_ADMIN,ROLE_USER));
        if(users.getUserInfo()!=null)

            users.getUserInfo().setFirstName(users.getUserInfo().getFirstName().toUpperCase());
            users.getUserInfo().setLastName(users.getUserInfo().getLastName().toUpperCase());
            users.getUserInfo().setGender(users.getUserInfo().getGender().toUpperCase());

            users.getUserInfo().setUsers(users);

        Users saved = repository.save(users);
        log.info("User registered successfully with id: {}", saved.getUsrId());


        return new UserResponse(
                saved.getId(),
                saved.getUsrId(),
                saved.getEmail(),
                saved.getPhoneNumber(),
                saved.getRoles().stream().map(r->"ROLES_"+r).toList(),
                mapper.toInfoResponse(saved.getUserInfo())
        );
    }

    @Override
    public UserResponse user(UserRequest request) {

        log.info("Registering User for admin role");

        Users users = mapper.toAuthUser(request);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRoles(Set.of(ROLE_USER));
        if(users.getUserInfo()!=null)

            users.getUserInfo().setFirstName(users.getUserInfo().getFirstName().toUpperCase());
            users.getUserInfo().setLastName(users.getUserInfo().getLastName().toUpperCase());
            users.getUserInfo().setGender(users.getUserInfo().getGender().toUpperCase());

        users.getUserInfo().setUsers(users);

        Users saved = repository.save(users);
        log.info("User registered successfully with id: {}", saved.getUsrId());


        return new UserResponse(
                saved.getId(),
                saved.getUsrId(),
                saved.getEmail(),
                saved.getPhoneNumber(),
                saved.getRoles().stream().map(r->"ROLES_"+r).toList(),
                mapper.toInfoResponse(saved.getUserInfo())
        );
    }

    @Override
    public LoginResponse login(LoginRequest request) throws UserPrincipalNotFoundException {

        log.info("login request received");
        Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if (!authenticate.isAuthenticated())
            throw new UserPrincipalNotFoundException("Invalid credentials");
        Users user = (Users) authenticate.getPrincipal();
        assert user != null;
        String token = jwtService.generateToken(user);
        return new LoginResponse(
                LocalDateTime.now(),
                "HI User Mr: "+user.getUserInfo().getFirstName() +" "+user.getUserInfo().getLastName()+" Welcome to SHOP.IN , USER Authentication You Are Authenticated",
                token
        );


    }
}
