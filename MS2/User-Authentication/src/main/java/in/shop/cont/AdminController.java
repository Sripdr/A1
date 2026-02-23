package in.shop.cont;

import in.shop.dto.AuthReq;
import in.shop.dto.AuthRes;
import in.shop.serv.AuthService;
import in.shop.serv.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AuthService authService;
    private final UserService userService;


    @PutMapping("/updateById/{userId}")
    public ResponseEntity<AuthRes> update(@PathVariable Long userId, @Valid @RequestBody AuthReq authReq) {
        AuthRes res = userService.update(userId, authReq);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/updateBYUsername/{username}")
    public ResponseEntity<AuthRes> updateByUsername(@PathVariable String username, @Valid @RequestBody AuthReq authReq) {
        AuthRes res = userService.update(username, authReq);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<AuthRes>> findAll() {
        List<AuthRes> resList = authService.findAll();
        return new ResponseEntity<>(resList, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long userId) {
        authService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
