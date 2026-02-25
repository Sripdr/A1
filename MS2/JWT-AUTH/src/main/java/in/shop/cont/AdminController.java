package in.shop.cont;

import in.shop.dto.AuthRequest;
import in.shop.dto.AuthResponse;
import in.shop.entity.AuthUser;
import in.shop.serv.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/findBYId/{userId}")
    public ResponseEntity<AuthResponse> findById(@PathVariable Long userId){
        return ResponseEntity.ok(adminService.findById(userId));
    }

    @GetMapping("/findyUsername/{username}")
    public ResponseEntity<AuthResponse> findByUserNmae(@PathVariable String username){
        AuthResponse byUsername = adminService.findByUsername(username);
        return ResponseEntity.ok(byUsername);
    }

    @GetMapping("/findByPhone/{phoneNumber}")
    public ResponseEntity<AuthResponse> findByPhoneNumber(@PathVariable String phoneNumber){
        AuthResponse byPhoneNumber = adminService.findByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(byPhoneNumber);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<AuthResponse>> findAll(){
        List<AuthResponse> all = adminService.findAll();
        return ResponseEntity.ok(all);
    }

    @PutMapping("/updateByPhone/{phoneNumber}")
    private ResponseEntity<AuthResponse> updateByPhoneNumber(@PathVariable String phoneNumber, @Valid @RequestBody AuthRequest authRequest){
        AuthResponse authResponse = adminService.updateByPhoneNumber(phoneNumber, authRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PutMapping
    public ResponseEntity<AuthResponse> updateByUsername(@PathVariable String username, @Valid @RequestBody AuthRequest authRequest){
        AuthResponse authResponse = adminService.updateByUsername(username, authRequest);
        return ResponseEntity.ok(authResponse);
    }
}
