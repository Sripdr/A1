package in.shop.service.impl;

import in.shop.dto.user.UserResponse;
import in.shop.entity.Users;
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
    public UserResponse findById(String usrId) {
        log.info(REQUEST+" usrId"+ usrId);

        Users users = repository.findByUsrId(usrId).orElseThrow(() -> new NotFoundException("Requested for " + NOT_FOUND + usrId));
        return new UserResponse(
                users.getId(),
                users.getUsrId(),
                users.getEmail(),
                users.getPhoneNumber(),
                users.getRoles().stream().map(r->"ROLES_"+r).toList(),
                mapper.toInfoResponse(users.getUserInfo())
        );
    }

    @Override
    public UserResponse findByEmail(String email) {
        log.info(REQUEST+"for email "+email);

        Users users = repository.findByEmail(email).orElseThrow(() -> new NotFoundException("Requested for " + NOT_FOUND + email));
        return new UserResponse(
                users.getId(),
                users.getUsrId(),
                users.getEmail(),
                users.getPhoneNumber(),
                users.getRoles().stream().map(r->"ROLES_"+r).toList(),
                mapper.toInfoResponse(users.getUserInfo()));
    }


    @Override
    public UserResponse findByPhone(String phoneNumber) {
        return null;
    }

    @Override
    public List<UserResponse> findAll() {
        log.info("Finding all users");
        List<Users> users = repository.findAll();
        return users.stream().map(authUser ->
                new UserResponse(
                        authUser.getId(),
                        authUser.getUsrId(),
                        authUser.getEmail(),
                        authUser.getPhoneNumber(),
                        authUser.getRoles().stream().map(r->"ROLES_"+r).toList(),
                        mapper.toInfoResponse(authUser.getUserInfo()))
                ).toList();
    }

    @Override
    public void updateFirstName(String usrId, String firstName) {

        log.info("Updating first name for user with id"+usrId);

        Users users = repository.findByUsrId(usrId).orElseThrow(() -> new NotFoundException("Requested for " + NOT_FOUND + usrId));

        users.getUserInfo().setFirstName(firstName.toUpperCase());
        repository.save(users);

        log.info("First name updated successfully for authId"+usrId);

    }

    @Override
    public void updateLastName(String usrId, String lastName) {
        log.info("Updating last name for user with id"+usrId);

        Users users = repository.findByUsrId(usrId).orElseThrow(() -> new NotFoundException("Requested for " + NOT_FOUND + usrId));
        users.getUserInfo().setLastName(lastName.toUpperCase());
        repository.save(users);

        log.info("Last name updated successfully for usrId " +usrId);
    }

    @Override
    public void updateDateOfBirth(String usrId, LocalDate dateOfBirth) {
        log.info("Updating date of birth for user with id"+usrId);

        Users users = repository.findByUsrId(usrId).orElseThrow(() -> new NotFoundException("Requested for " + NOT_FOUND + usrId));
        users.getUserInfo().setDateOfBirth(dateOfBirth);
        repository.save(users);

        log.info("Date of birth updated successfully for usrId "+usrId);
    }

    @Override
    public void deleteUser(String usrId) {
        log.info("Deleting user with id "+ usrId);

        Users users = repository.findByUsrId(usrId).orElseThrow(() -> new NotFoundException("Requested for " + NOT_FOUND + usrId));
        repository.delete(users);
        log.info("User deleted successfully for authId"+usrId);

    }

    @Override
    public void deleteByEmail(String email) {
        Users users = repository.findByEmail(email).orElseThrow(() -> new NotFoundException("Requested for " + NOT_FOUND + email));
        log.info("Deleting user with email"+email);
        repository.delete(users);
        log.info("User deleted successfully for email"+email);
    }
}
