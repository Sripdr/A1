package in.shop.service.impl;

import in.shop.dto.UserResponseDto;
import in.shop.entity.AuthUser;
import in.shop.exception.UserNotFoundException;
import in.shop.mapper.DtoMapper;
import in.shop.repository.UserRepository;
import in.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;


    @Override
    public UserResponseDto findUserById(Long userId) {
        log.info("get User ById {}",userId);
        AuthUser authUser = repository.findById(userId).orElseThrow(() -> new UserNotFoundException("Requested User is not Found wih id{}" + userId));
        return DtoMapper.mapToDto(authUser);
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        List<AuthUser> all = repository.findAll();
        return all.stream().map(DtoMapper::mapToDto).toList();
    }

    @Override
    public UserResponseDto updateUserFirstName(Long userId, String firstName) {

        log.info("update User FirstName {}",userId);
        AuthUser authUser = repository.findById(userId).orElseThrow(() -> new UserNotFoundException("Requested User is not Found wih id{}" + userId));
        authUser.setFirstName(firstName.toUpperCase());
        authUser.setUpdatedDate(LocalDateTime.now());
        AuthUser saved = repository.save(authUser);

        return DtoMapper.mapToDto(saved);
    }

    @Override
    public UserResponseDto updateUserLastName(Long userId, String lastName) {

        log.info("update User LastName {}",userId);
        AuthUser authUser = repository.findById(userId).orElseThrow(() -> new UserNotFoundException("Requested User is not Found wih id{}" + userId));
        authUser.setLastName(lastName.toUpperCase());
        authUser.setUpdatedDate(LocalDateTime.now());
        AuthUser saved = repository.save(authUser);
        return DtoMapper.mapToDto(saved);
    }

    @Override
    public UserResponseDto updateUserPhoneNumber(Long userId, String phoneNumber) {
        log.info("updateUserFirstName {}",userId);
        AuthUser authUser = repository.findById(userId).orElseThrow(() -> new UserNotFoundException("Requested User is not Found wih id{}" + userId));
        authUser.setPhoneNumber("+91"+phoneNumber);
        authUser.setUpdatedDate(LocalDateTime.now());
        AuthUser saved = repository.save(authUser);
        return DtoMapper.mapToDto(saved);
    }

    @Override
    public UserResponseDto updateUserDateOfBirth(Long userId, String dateOfBirth) {
        log.info("updateUserFirstName {}",userId);
        AuthUser authUser = repository.findById(userId).orElseThrow(() -> new UserNotFoundException("Requested User is not Found wih id{}" + userId));
        authUser.setDateOfBirth(LocalDate.parse(dateOfBirth));
        authUser.setUpdatedDate(LocalDateTime.now());
        AuthUser saved = repository.save(authUser);
        return DtoMapper.mapToDto(saved);
    }
}
