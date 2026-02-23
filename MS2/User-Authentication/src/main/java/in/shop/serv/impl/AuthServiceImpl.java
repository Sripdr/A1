package in.shop.serv.impl;

import in.shop.dto.AuthReq;
import in.shop.dto.AuthRes;
import in.shop.dto.Login;
import in.shop.entity.AuthUser;
import in.shop.excep.UserExist;
import in.shop.excep.UserNotFound;
import in.shop.map.DtoMapper;
import in.shop.repo.AuthRepo;
import in.shop.serv.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepo authRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthRes register(AuthReq authReq) throws UserExist {

        AuthUser authUser = DtoMapper.mapToAuthUser(authReq);
        log.info("Mapped AuthUser: {}", authUser.toString());

        String username=authUser.getUsername();
        if (authRepo.findByUsername(username).isPresent()) {
            throw new UserExist("User is Already Exist in Database with Given username : "+username);
        }
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUser.setCreatedAt(LocalDateTime.now());
        authUser.setPhoneNumber("+91 " + authUser.getPhoneNumber());
        AuthUser saved = authRepo.save(authUser);
        log.info("Saved AuthUser: {}", saved.toString());
        return DtoMapper.mapToAuthRes(saved);
    }

    @Override
    public String login(Login login) {

        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        if (!authenticate.isAuthenticated()) {
            throw new UserNotFound("Invalid username or password"+login.getUsername());
        }

        return "Current Time is : "+ LocalDateTime.now()
                +"\n User logged in successfully with username: " +login.getUsername().toUpperCase();
    }

    @Override
    public List<AuthRes> findAll() {

        List<AuthUser> authUsers = authRepo.findAll();

        return authUsers.stream()
                .map(DtoMapper::mapToAuthRes)
                .toList();

    }

    @Override
    public void deleteById(Long userId) {
        assert authRepo != null;
        authRepo.findById(userId).orElseThrow(
                () -> new UserNotFound("User not found with id: " + userId));

        authRepo.deleteById(userId);

    }

}
