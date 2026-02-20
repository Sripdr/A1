package in.shop.controller;

import in.shop.dto.AuthRequest;
import in.shop.dto.AuthResponse;
import in.shop.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/reg")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest authRequest) {

        AuthResponse register = authService.register(authRequest);
        return new ResponseEntity<>(register, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<AuthResponse> updateUser(@PathVariable Long userId,@Valid @RequestBody AuthRequest authRequest) {
        AuthResponse update = authService.update(userId, authRequest);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<AuthResponse> findById(@PathVariable Long userId) {

        return ResponseEntity.ok().body(authService.findUser(userId));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<AuthResponse> findByUserName(@PathVariable String username) {
        return ResponseEntity.ok().body(authService.findUserByUserName(username));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<AuthResponse>> findAll() {

        return ResponseEntity.ok().body(authService.findAllUsers());
    }

    @DeleteMapping("/userId/{userId}")
    public void deleteById(@PathVariable Long userId) {
        authService.deleteUserById(userId);

    }

}
