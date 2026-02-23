package in.shop.cont;

import in.shop.dto.AuthReq;
import in.shop.dto.AuthRes;
import in.shop.serv.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import in.shop.serv.UserService;

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
    public ResponseEntity<String> login( String username,  String password) {
        String token = authService.login(username, password);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<AuthRes>> findAll() {
        List<AuthRes> resList = authService.findAll();
        return new ResponseEntity<>(resList, HttpStatus.OK);
    }

}
