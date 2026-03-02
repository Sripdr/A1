package in.shop.controller;

import in.shop.dto.LoginRequest;
import in.shop.dto.LoginResponse;
import in.shop.dto.UserRegisterRequest;
import in.shop.service.UserAuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Shop/AuthService/Auth")
@RequiredArgsConstructor
@EnableMethodSecurity
public class AuthController {

    private final UserAuthenticationService userAuthenticationService;

    @PostMapping("/RegisterAsAdmin")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        String success = userAuthenticationService.registerUserAsAdmin(userRegisterRequest);
        return new ResponseEntity<>(success, HttpStatus.CREATED);
    }

    @PostMapping("/RegisterAsUser")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        String success = userAuthenticationService.registerUserAsUser(userRegisterRequest);
        return new ResponseEntity<>(success, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> logInUser(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse login = userAuthenticationService.login(loginRequest);
        return  new ResponseEntity<>(login, HttpStatus.OK);
    }
}
