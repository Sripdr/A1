package in.shop.serv.impl;

import in.shop.dto.AuthUserResponse;
import in.shop.entity.AuthUser;
import in.shop.excep.UserNotFound;
import in.shop.map.DTOMapper;
import in.shop.repo.AuthRepository;
import in.shop.serv.AuthUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthRepository authRepository;

    @Override
    public List<AuthUserResponse> findAll() {
        List<AuthUser> all = authRepository.findAll();
        return all.stream().map(DTOMapper::mapToAuthResponse).toList();
    }

    @Override
    public AuthUserResponse findById(Long userId) {
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
    public AuthUserResponse findByUsername(String username) {
        log.info("Finding User by username "+username);
        AuthUser authUser = authRepository.findByUsername(username).orElseThrow(() ->
                new UserNotFound(" The User for Username:  " + username + " is not found."));
        return DTOMapper.mapToAuthResponse(authUser);
    }


    @Override
    public AuthUserResponse updateByFirstNameById(Long userId, String firstName) {
        AuthUser authUser = authRepository.findById(userId).orElseThrow(()-> new UserNotFound(" The User for Id:  "+userId+" is not found."));
        authUser.setFirstName(firstName.toUpperCase());
        authUser.setUpdatedAt(LocalDateTime.now());
        AuthUser updateuser = authRepository.save(authUser);
        log.info("Saved User "+updateuser.getUsername());
         return DTOMapper.mapToAuthResponse(updateuser);

    }

    @Override
    public AuthUserResponse updateByLastNameById(Long userId, String lastName) {
        AuthUser authUser = authRepository.findById(userId).orElseThrow(()-> new UserNotFound(" The User for Id:  "+userId+" is not found."));
        authUser.setLastName(lastName.toUpperCase());
        authUser.setUpdatedAt(LocalDateTime.now());
        AuthUser updateuser = authRepository.save(authUser);
        log.info("Saved User "+updateuser.getUsername());
        return DTOMapper.mapToAuthResponse(updateuser);

    }

    @Override
    public AuthUserResponse updatePhoneNumberById(Long userId, String phoneNumber) {
        AuthUser authUser = authRepository.findById(userId).orElseThrow(()-> new UserNotFound(" The User for Id:  "+userId+" is not found."));
        authUser.setPhoneNumber("+91"+phoneNumber);
        authUser.setUpdatedAt(LocalDateTime.now());
        AuthUser updateuser = authRepository.save(authUser);
        log.info("Saved User "+updateuser.getUsername());
        return DTOMapper.mapToAuthResponse(updateuser);
    }

    @Override
    public AuthUserResponse updateDateofBirth(Long userId, LocalDate dateOfBirth) {
        AuthUser authUser = authRepository.findById(userId).orElseThrow(()-> new UserNotFound(" The User for Id:  "+userId+" is not found."));
        authUser.setDateOfBirth(dateOfBirth);
        authUser.setUpdatedAt(LocalDateTime.now());
        AuthUser updateuser = authRepository.save(authUser);
        log.info("Saved User "+updateuser.getUsername());
        return DTOMapper.mapToAuthResponse(updateuser);

    }

}
