package in.shop.cont;

import in.shop.dto.*;
import in.shop.serv.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registerAdmin")
    public ResponseEntity<RegisterResponse> registerAdmin(@Valid @RequestBody RegisterRequest authRequest) {
        RegisterResponse registerResponse = authService.registerAdmin(authRequest);
        return   new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody RegisterRequest authRequest) {
        RegisterResponse registerResponse = authService.registerUser(authRequest);
        return   new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
        }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws UnsupportedEncodingException {
        LoginResponse login = authService.login(loginRequest);
        return  new ResponseEntity<>(login, HttpStatus.OK);
    }


}
