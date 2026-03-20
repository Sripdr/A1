package in.shop.service;

import in.shop.dto.user.AuthResponse;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    public AuthResponse findById(String authId);

    public AuthResponse findByEmail(String email);

    public AuthResponse findByPhone(String phone);

    public List<AuthResponse> findAll();

    public void updateFirstName(String authId, String firstName);

    public void updateLastName(String authId, String lastName);

    public void updateDateOfBirth(String authId, LocalDate dateOfBirth);

     public void deleteUser(String authId);

     public void deleteByEmail(String email);
}
