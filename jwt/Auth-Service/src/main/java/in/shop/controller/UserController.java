package in.shop.controller;

import in.shop.dto.user.AuthResponse;
import in.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static in.shop.util.AuthConstants.USER_URL;

@RestController
@RequestMapping(USER_URL)
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    @GetMapping("/{authId}")
    @PreAuthorize("#authId == authentication.principal.authId")
    public ResponseEntity<AuthResponse> findById(@PathVariable String authId) {
        AuthResponse response = service.findById(authId);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/firstName/{authId}")
    @PreAuthorize("#authId == authentication.principal.authId")
    public ResponseEntity<String> updateFirstName(@PathVariable String authId, @RequestBody String firstName) {
        service.updateFirstName(authId, firstName);
        return ResponseEntity.ok("First name updated successfully");
    }


    @PatchMapping("/lastName/{authId}")
    @PreAuthorize("#authId == authentication.principal.authId")
    public ResponseEntity<String> updateLastName(@PathVariable String authId, @RequestBody String lastName) {
        service.updateLastName(authId, lastName);
        return ResponseEntity.ok("Last name updated successfully");

    }


    @GetMapping("/email")
    @PreAuthorize("#email == authentication.principal.email")
    public  ResponseEntity<AuthResponse> findByEmail(@PathVariable String email) {

        AuthResponse response = service.findByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/phone")
    @PreAuthorize("#phone == authentication.principal.phone")
    public  ResponseEntity<AuthResponse> findByPhone(@PathVariable String phone) {

        AuthResponse response = service.findByPhone(phone);
        return ResponseEntity.ok(response);
    }



    @PatchMapping("/dateOfBirth/{authId}")
    @PreAuthorize("#authId == authentication.principal.authId")
    public ResponseEntity<String> updateDateOfBirth(@PathVariable String authId, @RequestBody LocalDate dateOfBirth) {
        service.updateDateOfBirth(authId, dateOfBirth);
        return ResponseEntity.ok("Date of birth updated successfully");
    }

}
