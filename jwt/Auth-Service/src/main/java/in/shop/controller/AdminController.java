package in.shop.controller;


import in.shop.dto.user.AuthResponse;
import in.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static in.shop.util.AuthConstants.ADMIN_URL;

@RestController
@RequestMapping(ADMIN_URL)
@RequiredArgsConstructor
public class AdminController {

    private final UserService service;

    @GetMapping("/{authId}")

    public ResponseEntity<AuthResponse> findById(@PathVariable String authId) {

        AuthResponse response = service.findById(authId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")

    public ResponseEntity<List<AuthResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/email")
    public  ResponseEntity<AuthResponse> findByEmail(@PathVariable String email) {

        AuthResponse response = service.findByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/phone")
    public  ResponseEntity<AuthResponse> findByPhone(@PathVariable String phone) {

        AuthResponse response = service.findByPhone(phone);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/firstName/{authId}")
    public ResponseEntity<String> updateFirstName(@PathVariable String authId, @RequestBody String firstName) {
        service.updateFirstName(authId, firstName);
        return ResponseEntity.ok("First name updated successfully");
    }

    @GetMapping("/lastName/{authId}")
    public ResponseEntity<String> updateLastName(@PathVariable String authId, @RequestBody String lastName) {
        service.updateLastName(authId, lastName);
        return ResponseEntity.ok("Last name updated successfully");

    }

    @GetMapping("/dateOfBirth/{authId}")
    public ResponseEntity<String> updateDateOfBirth(@PathVariable String authId, @RequestBody LocalDate dateOfBirth) {
        service.updateDateOfBirth(authId, dateOfBirth);
        return ResponseEntity.ok("Date of birth updated successfully");
    }


    @GetMapping("/delete/{authId}")
    public ResponseEntity<String> delete(@PathVariable String authId) {
        service.deleteUser(authId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/delete/{email}")
    public ResponseEntity<Void> deleteByEmail(@PathVariable String email) {
        service.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }

}
