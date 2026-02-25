package in.shop.cont;


import in.shop.dto.AuthRequest;
import in.shop.dto.AuthResponse;
import in.shop.serv.AdminService;
import in.shop.serv.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final AdminService adminService;

    @PutMapping("/updateById/{userId}")
    public ResponseEntity<AuthResponse> updateById(Long userId, @Valid @RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = adminService.updateById(userId, authRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PutMapping("/updateByUsername/{username}")
    public ResponseEntity<AuthResponse> updateByUsernmae(String username, @Valid @RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = adminService.updateByUsername(username, authRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PutMapping("/updateByPhone/{phoneNumber}")
    public ResponseEntity<AuthResponse> getByPhone(String phoneNumber,@Valid @RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = adminService.updateByPhoneNumber(phoneNumber, authRequest);
        return ResponseEntity.ok(authResponse);
    }
}
