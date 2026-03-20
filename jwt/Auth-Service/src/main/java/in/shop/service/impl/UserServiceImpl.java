package in.shop.service.impl;

import in.shop.dto.user.AuthResponse;
import in.shop.entity.AuthUser;
import in.shop.map.RequestAndResponseMapper;
import in.shop.repository.AuthRepository;
import in.shop.service.UserService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static in.shop.util.AuthConstants.NOT_FOUND;
import static in.shop.util.AuthConstants.REQUEST;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final AuthRepository repository;
    private final RequestAndResponseMapper  mapper;

    @Override
    public AuthResponse findById(String authId) {
        log.info(REQUEST+" authId"+ authId);

        AuthUser authUser = repository.findById(authId).orElseThrow(() -> new NotFoundException("Requested for " + NOT_FOUND + authId));
        return new AuthResponse(
                authUser.getAuthId(),
                authUser.getEmail(),
                authUser.getPhoneNumber(),
                authUser.getRoles().stream().map(r->"ROLES_"+r).toList(),
                mapper.toInfoResponse(authUser.getUserInfo())
        );
    }

    @Override
    public AuthResponse findByEmail(String email) {
        log.info(REQUEST+"for email "+email);

        AuthUser authUser = repository.findByEmail(email).orElseThrow(() -> new NotFoundException("Requested for " + NOT_FOUND + email));
        return new AuthResponse(authUser.getAuthId(),
                authUser.getEmail(),
                authUser.getPhoneNumber(),
                authUser.getRoles().stream().map(r->"ROLES_"+r).toList(),
                mapper.toInfoResponse(authUser.getUserInfo()));
    }


    @Override
    public AuthResponse findByPhone(String phone) {
        return null;
    }

    @Override
    public List<AuthResponse> findAll() {
        log.info("Finding all users");
        List<AuthUser> authUsers = repository.findAll();
        return authUsers.stream().map(authUser ->
                new AuthResponse(authUser.getAuthId(),
                        authUser.getEmail(),
                        authUser.getPhoneNumber(),
                        authUser.getRoles().stream().map(r->"ROLES_"+r).toList(),
                        mapper.toInfoResponse(authUser.getUserInfo()))
                ).toList();
    }

    @Override
    public void updateFirstName(String authId, String firstName) {

        log.info("Updating first name for user with id"+authId);

        AuthUser authUser = repository.findById(authId).orElseThrow(() -> new NotFoundException("Requested for " + NOT_FOUND + authId));

        authUser.getUserInfo().setFirstName(firstName.toUpperCase());
        repository.save(authUser);

        log.info("First name updated successfully for authId"+authId);

    }

    @Override
    public void updateLastName(String authId, String lastName) {
        log.info("Updating last name for user with id"+authId);

        AuthUser authUser = repository.findById(authId).orElseThrow(() -> new NotFoundException("Requested for " + NOT_FOUND + authId));
        authUser.getUserInfo().setLastName(lastName.toUpperCase());
        repository.save(authUser);

        log.info("Last name updated successfully for authId"+authId);
    }

    @Override
    public void updateDateOfBirth(String authId, LocalDate dateOfBirth) {
        log.info("Updating date of birth for user with id"+authId);

        AuthUser authUser = repository.findById(authId).orElseThrow(() -> new NotFoundException("Requested for " + NOT_FOUND + authId));
        authUser.getUserInfo().setDateOfBirth(dateOfBirth);
        repository.save(authUser);

        log.info("Date of birth updated successfully for authId"+authId);
    }

    @Override
    public void deleteUser(String authId) {
        log.info("Deleting user with id"+authId);

        AuthUser authUser = repository.findById(authId).orElseThrow(() -> new NotFoundException("Requested for " + NOT_FOUND + authId));
        repository.delete(authUser);
        log.info("User deleted successfully for authId"+authId);

    }

    @Override
    public void deleteByEmail(String email) {
        AuthUser authUser = repository.findByEmail(email).orElseThrow(() -> new NotFoundException("Requested for " + NOT_FOUND + email));
        log.info("Deleting user with email"+email);
        repository.delete(authUser);
        log.info("User deleted successfully for email"+email);
    }
}
