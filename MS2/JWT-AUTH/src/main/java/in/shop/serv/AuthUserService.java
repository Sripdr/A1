package in.shop.serv;

import in.shop.dto.AuthUserResponse;

import java.time.LocalDate;
import java.util.List;

public interface AuthUserService {
    List<AuthUserResponse> findAll();
   public AuthUserResponse findById(Long userId);
    void deleteById(Long userId);
    public AuthUserResponse findByUsername(String username);
    public AuthUserResponse updateByFirstNameById(Long userId, String firstName);
    public AuthUserResponse updateByLastNameById(Long userId, String lastName);
    public AuthUserResponse updatePhoneNumberById(Long userId, String phoneNumber);
    public AuthUserResponse updateDateofBirth(Long userId, LocalDate dateOfBirth);

}
