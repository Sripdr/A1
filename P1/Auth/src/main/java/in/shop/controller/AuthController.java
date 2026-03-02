package in.shop.controller;


import in.shop.dto.LoginDto;
import in.shop.dto.LoginResponseDto;
import in.shop.dto.UserRegisterDto;
import in.shop.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shop/authent/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserAuthenticationService service;

    @PostMapping("/admin")
    public ResponseEntity<String> registerAsAdmin(@RequestBody UserRegisterDto user) {

        String saved = service.registerUserAsAdmin(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/user")
    public ResponseEntity<String> registerAsUser(@RequestBody UserRegisterDto user) {

        String saved = service.registerUserAsUser(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto login){

        LoginResponseDto login1 = service.login(login);
        return  new ResponseEntity<>(login1, HttpStatus.OK);
    }
}
