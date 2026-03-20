package in.shop.controller;

import in.shop.dto.login.LoginRequest;
import in.shop.dto.login.LoginResponse;
import in.shop.dto.user.AuthRequest;
import in.shop.dto.user.AuthResponse;
import in.shop.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import static in.shop.util.AuthConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL+"/public")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/admin")
    public ResponseEntity<AuthResponse> admin(@Valid @RequestBody AuthRequest request){
        AuthResponse admin = authService.admin(request);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }
    @PostMapping("/user")
    public ResponseEntity<AuthResponse> user(@Valid@RequestBody AuthRequest request){

        AuthResponse user = authService.user(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) throws UserPrincipalNotFoundException {
        LoginResponse login = authService.login(request);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }
}
