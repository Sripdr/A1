package in.shop.serv;

import in.shop.dto.*;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

public interface AuthService {

        public RegisterResponse registerAdmin(RegisterRequest request);
        public RegisterResponse registerUser(RegisterRequest request);
        public LoginResponse login(LoginRequest request) throws UnsupportedEncodingException;

}
