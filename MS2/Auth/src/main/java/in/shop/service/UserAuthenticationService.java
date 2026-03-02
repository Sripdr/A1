package in.shop.service;

import in.shop.dto.LoginDto;
import in.shop.dto.LoginResponseDto;
import in.shop.dto.UserRegisterDto;
import in.shop.dto.UserResponseDto;

public interface UserAuthenticationService {

public String registerUserAsAdmin(UserRegisterDto request);
public String registerUserAsUser(UserRegisterDto request);
public LoginResponseDto login(LoginDto request);

}
