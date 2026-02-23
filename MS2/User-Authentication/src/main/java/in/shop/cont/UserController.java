package in.shop.cont;


import in.shop.dto.AuthReq;
import in.shop.dto.AuthRes;
import in.shop.serv.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private  final UserService userService;

    @GetMapping("/findById/{userId}")
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public ResponseEntity<AuthRes> findById(@PathVariable Long userId) {
        AuthRes res = userService.findById(userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/findBYUsername/{username}")
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public ResponseEntity<AuthRes> findByUsername(@PathVariable String username) {
        AuthRes res = userService.findByUsername(username);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/updateById/{userId}")
    public ResponseEntity<AuthRes> update(@PathVariable Long userId, @Valid @RequestBody AuthReq authReq) {
        AuthRes res = userService.update(userId, authReq);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/updateByUserName/{username}")
    public ResponseEntity<AuthRes> updateByUsername(@PathVariable String username, @Valid @RequestBody AuthReq authReq) {
        AuthRes res = userService.update(username, authReq);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
