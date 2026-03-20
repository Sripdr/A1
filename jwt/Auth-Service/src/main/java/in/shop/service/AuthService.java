package in.shop.service;

import in.shop.dto.login.LoginRequest;
import in.shop.dto.login.LoginResponse;
import in.shop.dto.user.AuthRequest;
import in.shop.dto.user.AuthResponse;

import java.nio.file.attribute.UserPrincipalNotFoundException;

public interface AuthService {

    public AuthResponse admin(AuthRequest request);

    public AuthResponse user(AuthRequest request);

    public LoginResponse login(LoginRequest request) throws UserPrincipalNotFoundException;
}
