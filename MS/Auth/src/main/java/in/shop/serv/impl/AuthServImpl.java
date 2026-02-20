package in.shop.serv.impl;

import in.shop.excep.UserExist;
import in.shop.excep.UserNotFound;
import in.shop.dto.AuthReq;
import in.shop.dto.AuthRes;
import in.shop.entity.AuthUser;
import in.shop.map.DtoMapper;
import in.shop.repo.AuthRepo;
import in.shop.serv.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServImpl implements AuthService {

    //private final ObjectMapper objectMapper = Mappers.getMapper(ObjectMapper.class);
    private final AuthRepo authRepo;



    @Override
    public AuthRes register(AuthReq authReq) throws UserExist {

        AuthUser authUser = DtoMapper.mapToAuthUser(authReq);
        log.info("Mapped AuthUser: {}", authUser.toString());

        String username=authUser.getUsername();
        if (authRepo.findByUsername(username).isPresent()) {
            throw new UserExist("User is Already Exist in Database with Given username : "+username);
        }

            authUser.setCreatedAt(LocalDateTime.now());
            authUser.setPhoneNumber("+91" + authUser.getPhoneNumber());
            AuthUser saved = authRepo.save(authUser);
            log.info("Saved AuthUser: {}", saved.toString());
            return DtoMapper.mapToAuthRes(saved);
    }

    @Override
    public AuthRes findByUsername(String username) {
        assert authRepo != null;
        AuthUser authUser = authRepo.findByUsername(username).orElseThrow(
                () -> new UserNotFound("User not found with username: " + username));

                return DtoMapper.mapToAuthRes(authUser);

    }

    @Override
    public AuthRes update(Long userId, AuthReq authReq) {
        assert authRepo != null;
        authRepo.findById(userId).orElseThrow(
                () -> new UserNotFound("User not found with id: " + userId));

         AuthUser authUser = DtoMapper.mapToAuthUser(authReq);
            authUser.setUpdatedAt(LocalDateTime.now());
         authUser.setUserId(userId);

         AuthUser updated = authRepo.save(authUser);

         return DtoMapper.mapToAuthRes(updated);

    }

    @Override
    public AuthRes findById(Long userId) {
        AuthUser authUser = authRepo.findById(userId).orElseThrow(
                () -> new UserNotFound("User not found with id: " + userId));

        return DtoMapper.mapToAuthRes(authUser);
    }

    @Override
    public List<AuthRes> findAll() {
        assert authRepo != null;
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
