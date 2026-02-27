package in.shop.cont;

import in.shop.dto.AuthUserResponse;
import in.shop.dto.RegisterRequest;
import in.shop.serv.AuthUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/authapi/shop/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final AuthUserService authUserService;


    @GetMapping("/findBYId/{userId}")
    public ResponseEntity<AuthUserResponse> findById(@PathVariable Long userId){
        return ResponseEntity.ok(authUserService.findById(userId));
    }

    @GetMapping("/findyUsername/{username}")
    public ResponseEntity<AuthUserResponse> findByUserNmae(@PathVariable String username){
        AuthUserResponse byUsername = authUserService.findByUsername(username);
        return ResponseEntity.ok(byUsername);
    }


    @GetMapping("/findAll")
    public ResponseEntity<List<AuthUserResponse>> findAll(){
        List<AuthUserResponse> all = authUserService.findAll();
        return ResponseEntity.ok(all);
    }

    @PatchMapping("/updateFistName/{userId}")
    public ResponseEntity<AuthUserResponse> updateFirstNameById(@PathVariable Long userId, @RequestBody RegisterRequest request ){
        AuthUserResponse authUserResponse = authUserService.updateByFirstNameById(userId, request.firstName());
        return ResponseEntity.ok(authUserResponse);
    }

    @PatchMapping("/updateLastName/{userId}")
    public ResponseEntity<AuthUserResponse> updateLastNameById(@PathVariable Long userId,  @RequestBody RegisterRequest request ){
        AuthUserResponse authUserResponse = authUserService.updateByLastNameById(userId, request.lastName());
        return ResponseEntity.ok(authUserResponse);
    }

    @PatchMapping("/updatePhoneNumber/{userId}")
    public ResponseEntity<AuthUserResponse> updateByPhoneNumber(@PathVariable Long userId, @Valid @RequestBody RegisterRequest request ){
        AuthUserResponse authUserResponse = authUserService.updatePhoneNumberById(userId, request.phoneNumber());
        return ResponseEntity.ok(authUserResponse);
    }

    @PatchMapping("/updateDateOfBirth/{userId}")
    public ResponseEntity<AuthUserResponse> updateDateOfBirth(@PathVariable Long userId,@Valid @RequestBody RegisterRequest request ){
        AuthUserResponse authUserResponse = authUserService.updateDateofBirth(userId, LocalDate.parse(request.dateOfBirth()));
        return ResponseEntity.ok(authUserResponse);
    }

    @DeleteMapping("/deleteById/{userId}")
    public ResponseEntity<String> deleteById(@PathVariable Long userId ) {
            authUserService.deleteById(userId);
            return ResponseEntity.ok(" User Deleted Successfully : "+userId);
        }


}
