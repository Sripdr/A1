package in.shop.cont;


import in.shop.dto.AuthUserResponse;
import in.shop.serv.AuthUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthUserService authUserService;


    @GetMapping("/findBYId/{userId}")
    @PostAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<AuthUserResponse> findById(@PathVariable Long userId){
        return ResponseEntity.ok(authUserService.findById(userId));
    }

    @PatchMapping("/updateFistName/{userId}/{firstName}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<AuthUserResponse> updateFirstNameById(@PathVariable Long userId, @PathVariable String firstName ){
        AuthUserResponse authUserResponse = authUserService.updateByFirstNameById(userId, firstName);
        return ResponseEntity.ok(authUserResponse);
    }

    @PatchMapping("/updateLastName/{userId}/{lastName}")
    @PreAuthorize("#userId == authentication.principal.userId ")
    public ResponseEntity<AuthUserResponse> updateLastNameById(@PathVariable Long userId,@PathVariable String lastName ){
        AuthUserResponse authUserResponse = authUserService.updateByLastNameById(userId, lastName);
        return ResponseEntity.ok(authUserResponse);
    }

    @PatchMapping("/updatePhoneNumber/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId ")
    public ResponseEntity<AuthUserResponse> updateByPhoneNumber(@PathVariable Long userId, @Valid @RequestBody String phoneNumber ){
        AuthUserResponse authUserResponse = authUserService.updatePhoneNumberById(userId, phoneNumber);
        return ResponseEntity.ok(authUserResponse);
    }

    @PatchMapping("/updateDateOfBirth/{userId}")
    public ResponseEntity<AuthUserResponse> updateDateOfBirth(@PathVariable Long userId, @Valid @RequestBody LocalDate dateOfBirth ){
        AuthUserResponse authUserResponse = authUserService.updateDateofBirth(userId, dateOfBirth);
        return ResponseEntity.ok(authUserResponse);
    }



}
