package in.shop.service;

import in.shop.dto.AuthResponse;
import in.shop.dto.LoginRequest;
import in.shop.dto.LoginResponse;
import in.shop.dto.UserRegisterRequest;

public interface UserAuthenticationService {

public String registerUserAsAdmin(UserRegisterRequest request);
public String registerUserAsUser(UserRegisterRequest request);
public LoginResponse login(LoginRequest request);

}
