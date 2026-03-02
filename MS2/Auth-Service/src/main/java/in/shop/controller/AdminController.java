package in.shop.controller;

import in.shop.dto.AuthResponse;
import in.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Shop/AuthService/Admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("'/findById/{iserId}")
    public ResponseEntity<AuthResponse> findUerById(@PathVariable Long userId){
        AuthResponse user = userService.getUserById(userId);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<AuthResponse>> finaAllUsers(){
        List<AuthResponse> allUsers = userService.findAllUsers();
        return  new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    @PatchMapping("/UpdateFirstName/{userId}")
    public ResponseEntity<AuthResponse> updateFirstName(@PathVariable Long userId ,@RequestBody String firstName){
        AuthResponse authResponse = userService.updateUserFirstName(userId,firstName);
        return ResponseEntity.ok(authResponse);
    }

    @PatchMapping("/UpdateLastName/{userId}")
    public ResponseEntity<AuthResponse> updateLastName(@PathVariable Long userId ,@RequestBody String lastName){
        AuthResponse authResponse = userService.updateUserLastName(userId, lastName);
        return  ResponseEntity.ok(authResponse);
    }

    @PatchMapping("/updatePhoneNumber/{phoneNumber}")
    public  ResponseEntity<AuthResponse> updatePhoneNumber(@PathVariable Long userId ,@RequestBody String phoneNumber){
        AuthResponse authResponse = userService.updateUserPhoneNumber(userId, phoneNumber);
        return ResponseEntity.ok(authResponse);
    }
    @PatchMapping("/updateDateOfBirth/{dateOfBirth}")
    public   ResponseEntity<AuthResponse> updateDateOfBirth(@PathVariable Long userId ,@RequestBody String dateOfBirth){
        AuthResponse authResponse = userService.updateUserDateOfBirth(userId, dateOfBirth);
        return  ResponseEntity.ok(authResponse);
    }
}
