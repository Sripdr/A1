package in.shop.service;

import in.shop.dto.user.UserResponse;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    public UserResponse findById(String usrId);

    public UserResponse findByEmail(String email);

    public UserResponse findByPhone(String phoneNumber);

    public List<UserResponse> findAll();

    public void updateFirstName(String usrId, String firstName);

    public void updateLastName(String usrId, String lastName);

    public void updateDateOfBirth(String usrId, LocalDate dateOfBirth);

     public void deleteUser(String usrId);

     public void deleteByEmail(String email);
}
