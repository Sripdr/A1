package in.shop.service.impl;

import in.shop.dto.AuthRequest;
import in.shop.dto.AuthResponse;
import in.shop.entity.AuthUser;
import in.shop.excep.UserNotFound;
import in.shop.map.EntityMapper;
import in.shop.repo.AuthRepo;
import in.shop.service.AuthService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuhServiceImpl implements AuthService {

    private final AuthRepo authRepo;
    private final EntityMapper entityMapper;


    @Override
    public AuthResponse register(AuthRequest authRequest) {
        AuthUser authUser = entityMapper.authRequestToAuthUser(authRequest);
        authUser.setPhoneNumber("+91" + authUser.getPhoneNumber());
        authUser.setCreatedAt(LocalDateTime.now());
       AuthUser saveUser = authRepo.save(authUser);
        return entityMapper.authUserToAuthResponse(saveUser);
    }

    @Override
    public AuthResponse update(Long userId, AuthRequest authRequest) {
        authRepo.findById(userId).orElseThrow(()->new UserNotFound("`user` not found with given Id"+userId));
        AuthUser authUser =entityMapper.authRequestToAuthUser(authRequest);
        authUser.getUpdatedAt(Instant.now());
        AuthUser saveUser = authRepo.save(authUser);
        return entityMapper.authUserToAuthResponse(saveUser);
    }

    @Override
    public AuthResponse findUser(Long userId) {
        AuthUser authUser = authRepo.findById(userId).orElseThrow(() -> new UserNotFound("`user` not found with given Id" + userId));
        return entityMapper.authUserToAuthResponse(authUser);

    }

    @Override
    public AuthResponse findUserByUserName(String userName) {
        AuthUser authUser = authRepo.findByUserName(userName).orElseThrow(() -> new UserNotFound("`user` not found with given Id" + userName));

        return entityMapper.authUserToAuthResponse(authUser);
    }

    @Override
    public List<AuthResponse> findAllUsers() {
        List<AuthUser> all = authRepo.findAll();
        return all.stream().map(entityMapper::authUserToAuthResponse).toList();
    }

    @Override
    public void deleteUserById(Long userId) {

        authRepo.findById(userId).orElseThrow(() -> new UserNotFound("`user` not found with given Id" + userId));

    }
}
