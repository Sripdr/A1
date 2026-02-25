package in.shop.serv.impl;

import in.shop.dto.AuthRequest;
import in.shop.dto.AuthResponse;
import in.shop.entity.AuthUser;
import in.shop.excep.UserNotFound;
import in.shop.map.DTOMapper;
import in.shop.repo.AuthRepository;
import in.shop.serv.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AuthRepository authRepository;

    @Override
    public List<AuthResponse> findAll() {
        List<AuthUser> all = authRepository.findAll();
        return all.stream().map(DTOMapper::mapToAuthResponse).toList();
    }

    @Override
    public AuthResponse findById(Long userId) {
        log.info("Finding User by id "+userId);
        AuthUser authUser = authRepository.findById(userId).orElseThrow(()-> new UserNotFound(" The User for Id:  "+userId+" is not found."));

        return DTOMapper.mapToAuthResponse(authUser);
    }

    @Override
    public void deleteById(Long userId) {
        log.info("Deleting User by id "+userId);
         findById(userId);
        authRepository.deleteById(userId);

    }

    @Override
    public AuthResponse findByUsername(String username) {
        log.info("Finding User by username "+username);
        AuthUser authUser = authRepository.findByUsername(username).orElseThrow(() ->
                new UserNotFound(" The User for Username:  " + username + " is not found."));
        return DTOMapper.mapToAuthResponse(authUser);
    }

    @Override
    public AuthResponse findByPhoneNumber(String phoneNumber) {
        log.info("Finding User by phone number "+phoneNumber);
        AuthUser authUser = authRepository.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new UserNotFound("The User Is Not Found with given Phone number : " + phoneNumber));
        return DTOMapper.mapToAuthResponse(authUser);
    }

    @Override
    public AuthResponse updateById(Long userId, AuthRequest authRequest) {
        log.info("Updating User by id "+userId);
        findById(userId);
        AuthUser authUser = DTOMapper.mapToAuthUser(authRequest);
        authUser.setUpdatedAt(LocalDateTime.now());
        AuthUser save = authRepository.save(authUser);
        log.info("Saved User "+save.getUsername());
        return DTOMapper.mapToAuthResponse(save);
    }

    @Override
    public AuthResponse updateByPhoneNumber(String phoneNumber, AuthRequest authRequest) {
        findByPhoneNumber(phoneNumber);
        log.info("Updating User by phone number "+phoneNumber);
        AuthUser authUser = DTOMapper.mapToAuthUser(authRequest);
       authUser.setUpdatedAt(LocalDateTime.now());
        AuthUser save = authRepository.save(authUser);
        log.info("Saved User "+save.getUsername());

        return DTOMapper.mapToAuthResponse(save);
    }

    @Override
    public AuthResponse updateByUsername(String username, AuthRequest authRequest) {
        findByUsername(username);
        log.info("Updating User by username "+username);
        AuthUser authUser = DTOMapper.mapToAuthUser(authRequest);
        authUser.setUpdatedAt(LocalDateTime.now());
        AuthUser save = authRepository.save(authUser);
        return DTOMapper.mapToAuthResponse(save);
    }
}
