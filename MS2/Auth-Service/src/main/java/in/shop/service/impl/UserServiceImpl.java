package in.shop.service.impl;

import in.shop.dto.AuthResponse;
import in.shop.entity.AuthUser;
import in.shop.exception.UserNotFoundException;
import in.shop.mapper.DTOMapper;
import in.shop.repository.UserAuthRepository;
import in.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserAuthRepository repository;

    @Override
    public AuthResponse getUserByUsername(String username) {

        return null;
    }

    @Override
    public AuthResponse getUserById(Long userId) {
        log.info("getUserById {}",userId);
        AuthUser authUser = repository.findById(userId).orElseThrow(() -> new UserNotFoundException("Requested User is not Found wih id{}" + userId));
        return DTOMapper.mapToResponse(authUser);
    }

    @Override
    public List<AuthResponse> findAllUsers() {
        List<AuthUser> all = repository.findAll();
        return all.stream().map(DTOMapper::mapToResponse).toList();
    }

    @Override
    public AuthResponse updateUserFirstName(Long userId, String userFirstName) {
        log.info("updateUserFirstName {}",userId);
        AuthUser authUser = repository.findById(userId).orElseThrow(() -> new UserNotFoundException("Requested User is not Found wih id{}" + userId));
        authUser.setUserFirstName(userFirstName);
        authUser.setUserUpdatedDate(LocalDateTime.now());
        AuthUser saved = repository.save(authUser);
        return DTOMapper.mapToResponse(saved);
    }

    @Override
    public AuthResponse updateUserLastName(Long userId, String userLastName) {
        log.info("updateUserLastName {}",userId);
        AuthUser authUser = repository.findById(userId).orElseThrow(() -> new UserNotFoundException("Requested User is not Found wih id{}" + userId));
        authUser.setUserLastName(userLastName);
        authUser.setUserUpdatedDate(LocalDateTime.now());
        AuthUser saved = repository.save(authUser);
        return DTOMapper.mapToResponse(saved);
    }

    @Override
    public AuthResponse updateUserPhoneNumber(Long userId, String phoneNumber) {
        log.info("updateUserPhoneNumber {}",userId);
       AuthUser authUser = repository.findById(userId).orElseThrow(() -> new UserNotFoundException("Requested User is not Found wih id{}" + userId));
        authUser.setUserPhoneNumber(phoneNumber);
        authUser.setUserUpdatedDate(LocalDateTime.now());
        AuthUser saved = repository.save(authUser);
        return DTOMapper.mapToResponse(saved);
    }

    @Override
    public AuthResponse updateUserDateOfBirth(Long userId, String DateOfBirth) {
AuthUser authUser = repository.findById(userId).orElseThrow(() -> new UserNotFoundException("Requested User is not Found wih id{}" + userId));
        authUser.setUserDateOfBirth(LocalDate.parse(DateOfBirth));
        authUser.setUserUpdatedDate(LocalDateTime.now());
        AuthUser saved = repository.save(authUser);
        return DTOMapper.mapToResponse(saved);
    }


}
