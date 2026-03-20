package in.shop.controller;


import in.shop.dto.user.UserResponse;
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

    @GetMapping("/{usrId}")

    public ResponseEntity<UserResponse> findById(@PathVariable String usrId) {

        UserResponse response = service.findById(usrId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")

    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/email")
    public  ResponseEntity<UserResponse> findByEmail(@PathVariable String email) {

        UserResponse response = service.findByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/phone")
    public  ResponseEntity<UserResponse> findByPhone(@PathVariable String phone) {

        UserResponse response = service.findByPhone(phone);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/firstName/{usrId}")
    public ResponseEntity<String> updateFirstName(@PathVariable String usrId, @RequestBody String firstName) {
        service.updateFirstName(usrId, firstName);
        return ResponseEntity.ok("First name updated successfully");
    }

    @GetMapping("/lastName/{usrId}")
    public ResponseEntity<String> updateLastName(@PathVariable String usrId, @RequestBody String lastName) {
        service.updateLastName(usrId, lastName);
        return ResponseEntity.ok("Last name updated successfully");

    }

    @GetMapping("/dateOfBirth/{usrId}")
    public ResponseEntity<String> updateDateOfBirth(@PathVariable String usrId, @RequestBody LocalDate dateOfBirth) {
        service.updateDateOfBirth(usrId, dateOfBirth);
        return ResponseEntity.ok("Date of birth updated successfully");
    }


    @GetMapping("/delete/{usrId}")
    public ResponseEntity<String> delete(@PathVariable String usrId) {
        service.deleteUser(usrId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/delete/{email}")
    public ResponseEntity<Void> deleteByEmail(@PathVariable String email) {
        service.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }

}
