package in.shop.controller;

import in.shop.dto.user.UserResponse;
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
    @GetMapping("/{usrId}")
    @PreAuthorize("#usrId == authentication.principal.usrId")
    public ResponseEntity<UserResponse> findById(@PathVariable String usrId) {
        UserResponse response = service.findById(usrId);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/firstName/{usrId}")
    @PreAuthorize("#usrId == authentication.principal.usrId")
    public ResponseEntity<String> updateFirstName(@PathVariable String usrId, @RequestBody String firstName) {
        service.updateFirstName(usrId, firstName);
        return ResponseEntity.ok("First name updated successfully");
    }


    @PatchMapping("/lastName/{usrId}")
    @PreAuthorize("#usrId == authentication.principal.usrId")
    public ResponseEntity<String> updateLastName(@PathVariable String usrId, @RequestBody String lastName) {
        service.updateLastName(usrId, lastName);
        return ResponseEntity.ok("Last name updated successfully");

    }


    @GetMapping("/email")
    @PreAuthorize("#email == authentication.principal.email")
    public  ResponseEntity<UserResponse> findByEmail(@PathVariable String email) {

        UserResponse response = service.findByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/phone")
    @PreAuthorize("#phoneNumber == authentication.principal.phoneNumber")
    public  ResponseEntity<UserResponse> findByPhone(@PathVariable String phoneNumber) {

        UserResponse response = service.findByPhone(phoneNumber);
        return ResponseEntity.ok(response);
    }



    @PatchMapping("/dateOfBirth/{usrId}")
    @PreAuthorize("#usrId == authentication.principal.usrId")
    public ResponseEntity<String> updateDateOfBirth(@PathVariable String usrId, @RequestBody LocalDate dateOfBirth) {
        service.updateDateOfBirth(usrId, dateOfBirth);
        return ResponseEntity.ok("Date of birth updated successfully");
    }

}
