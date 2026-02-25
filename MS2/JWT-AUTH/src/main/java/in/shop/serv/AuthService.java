package in.shop.serv;

import in.shop.dto.AuthRequest;
import in.shop.dto.AuthResponse;
import in.shop.dto.LoginRequest;
import in.shop.dto.LoginResponse;

import java.util.List;

public interface AuthService {

        public AuthResponse register(AuthRequest  request);
        public LoginResponse login(LoginRequest request);

}
