package in.shop.service;

import in.shop.dto.login.LoginRequest;
import in.shop.dto.login.LoginResponse;
import in.shop.dto.user.UserRequest;
import in.shop.dto.user.UserResponse;

import java.nio.file.attribute.UserPrincipalNotFoundException;

public interface AuthService {

    public UserResponse admin(UserRequest request);

    public UserResponse user(UserRequest request);

    public LoginResponse login(LoginRequest request) throws UserPrincipalNotFoundException;
}
