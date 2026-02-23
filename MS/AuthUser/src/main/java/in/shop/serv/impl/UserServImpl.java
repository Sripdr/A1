package in.shop.serv.impl;

import in.shop.excep.UserExist;
import in.shop.excep.UserNotFound;
import in.shop.dto.AuthReq;
import in.shop.dto.AuthRes;
import in.shop.entity.AuthUser;
import in.shop.map.DtoMapper;
import in.shop.repo.AuthRepo;
import in.shop.serv.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServImpl implements UserService {

    //private final ObjectMapper objectMapper = Mappers.getMapper(ObjectMapper.class);
    private final AuthRepo authRepo;

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
    public AuthRes update(String username, AuthReq authReq) {
        assert authRepo != null;
         authRepo.findByUsername(username).orElseThrow(
                () -> new UserNotFound("User not found with username: " + username));
         AuthUser authUser = DtoMapper.mapToAuthUser(authReq);
            authUser.setUpdatedAt(LocalDateTime.now());
         authUser.setUsername(username);

         AuthUser updated = authRepo.save(authUser);

         return DtoMapper.mapToAuthRes(updated);
    }



}
