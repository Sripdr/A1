package in.shop.cont;

import in.shop.dto.AuthReq;
import in.shop.dto.AuthRes;
import in.shop.dto.Login;
import in.shop.serv.AuthService;
import in.shop.serv.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthContr {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthRes> register(@Valid @RequestBody AuthReq authreq) {
        AuthRes res = authService.register(authreq);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        String token = authService.login(login);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }


}
