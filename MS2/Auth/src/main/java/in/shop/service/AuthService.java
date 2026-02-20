package in.shop.service;

import in.shop.dto.AuthRequest;
import in.shop.dto.AuthResponse;

import java.util.List;

public interface AuthService {
    AuthResponse register(AuthRequest authRequest);
    AuthResponse update(Long userId,AuthRequest authRequest);
    AuthResponse findUser(Long userId);
    AuthResponse findUserByUserName(String userName);
    List<AuthResponse> findAllUsers();
    void deleteUserById(Long userId);

}
