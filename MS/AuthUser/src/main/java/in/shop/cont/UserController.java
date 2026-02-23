package in.shop.cont;


import in.shop.dto.AuthReq;
import in.shop.dto.AuthRes;
import in.shop.serv.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private  final UserService userService;

    @GetMapping("/find/{userId}")
    public ResponseEntity<AuthRes> findById(@PathVariable Long userId) {
        AuthRes res = userService.findById(userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/find/username/{username}")
    public ResponseEntity<AuthRes> findByUsername(@PathVariable String username) {
        AuthRes res = userService.findByUsername(username);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }



}
