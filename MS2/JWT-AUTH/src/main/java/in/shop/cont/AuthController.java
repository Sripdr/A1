package in.shop.cont;

import in.shop.dto.AuthRequest;
import in.shop.dto.AuthResponse;
import in.shop.dto.LoginRequest;
import in.shop.dto.LoginResponse;
import in.shop.serv.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest authRequest) {
        AuthResponse register = authService.register(authRequest);
         return   new ResponseEntity<>(register, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse login = authService.login(loginRequest);
        return  new ResponseEntity<>(login, HttpStatus.OK);
    }


}
