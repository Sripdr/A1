package in.shop.config;


import in.shop.dto.UserResponseDto;
import in.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop/authent/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService service;

    @GetMapping("/findById/{userId}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long userId) {

        UserResponseDto userById = service.findUserById(userId);
        return ResponseEntity.ok(userById);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> allUsers = service.findAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @PatchMapping("/update/firstName/{userId}")
    public ResponseEntity<UserResponseDto> updateFirstName(@PathVariable Long userId, @RequestBody String firstName) {
        UserResponseDto updated = service.updateUserFirstName(userId, firstName);
        return  ResponseEntity.ok(updated);
    }

    @PatchMapping("/update/lastName/{userId}")
    public ResponseEntity<UserResponseDto> updateLastName(@PathVariable Long userId, @RequestBody String lastName) {
        UserResponseDto updated = service.updateUserLastName(userId,lastName);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/update/phone/{userId}")
    public ResponseEntity<UserResponseDto> updatePhoneNumber(@PathVariable Long userId, @RequestBody String phoneNumber) {
        UserResponseDto updated = service.updateUserPhoneNumber(userId, phoneNumber);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/update/dateOfBirth/{userId}")
    public ResponseEntity<UserResponseDto> updateDateOfBirth(@PathVariable Long userId, @RequestBody String  dateOfBirth) {
        UserResponseDto updated = service.updateUserDateOfBirth(userId, dateOfBirth);
        return ResponseEntity.ok(updated);

    }

    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        service.delete(userId);
        return ResponseEntity.ok(" The User is Deleted Successfully ");
    }
}
