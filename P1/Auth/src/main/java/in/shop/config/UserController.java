package in.shop.config;

import in.shop.dto.UserResponseDto;
import in.shop.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop/authent/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/findById/{userId}")
    @PostAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long userId) {
        UserResponseDto userById = service.findUserById(userId);
        return ResponseEntity.ok(userById);
    }


    @PatchMapping("/update/firstName/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<UserResponseDto> updateFirstName(@PathVariable Long userId, @Valid @NotNull @NotEmpty @RequestBody String firstName) {
        UserResponseDto updated = service.updateUserFirstName(userId, firstName);
        return  ResponseEntity.ok(updated);
    }

    @PatchMapping("/update/lastName/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<UserResponseDto> updateLastName(@PathVariable Long userId,@Valid @NotNull @NotEmpty @RequestBody String lastName ) {
        UserResponseDto updated = service.updateUserLastName(userId,lastName);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/update/phone/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<UserResponseDto> updatePhoneNumber(@PathVariable Long userId,@Valid @NotNull @NotEmpty @RequestBody String phoneNumber ) {
        UserResponseDto updated = service.updateUserPhoneNumber(userId, phoneNumber);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/update/dateOfBirth/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<UserResponseDto> updateDateOfBirth(@PathVariable Long userId,@Valid @NotNull @NotEmpty @RequestBody String dateOfBirth) {
        UserResponseDto updated = service.updateUserDateOfBirth(userId, dateOfBirth );
        return ResponseEntity.ok(updated);

    }
}
