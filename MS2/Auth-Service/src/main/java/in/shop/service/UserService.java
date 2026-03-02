package in.shop.service;

import in.shop.dto.AuthResponse;

import java.util.List;

public interface UserService {
    public AuthResponse getUserByUsername(String username);
    public AuthResponse getUserById(Long userId);
    public List<AuthResponse> findAllUsers();
    public AuthResponse updateUserFirstName(Long userId, String userFirstName);
    public  AuthResponse updateUserLastName(Long userId, String userLastName);
    public  AuthResponse updateUserPhoneNumber(Long userId, String phoneNumber);
    public   AuthResponse updateUserDateOfBirth(Long userId, String DateOfBirth);

}
