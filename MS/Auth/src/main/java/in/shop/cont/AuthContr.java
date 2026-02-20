package in.shop.cont;

import in.shop.dto.AuthReq;
import in.shop.dto.AuthRes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import in.shop.serv.AuthService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthContr {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthRes> register(@Valid @RequestBody AuthReq authreq) {
        AuthRes res = authService.register(authreq);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<AuthRes> findById(@PathVariable Long userId) {
        AuthRes res = authService.findById(userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/find/username/{username}")
    public ResponseEntity<AuthRes> findByUsername(@PathVariable String username) {
        AuthRes res = authService.findByUsername(username);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<AuthRes> update(@PathVariable Long userId, @Valid @RequestBody AuthReq authReq) {
        AuthRes res = authService.update(userId, authReq);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

        @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long userId) {
        authService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/id/{userId}")
    public ResponseEntity<AuthRes> updateByUsername(@PathVariable Long userId, @Valid @RequestBody AuthReq authReq) {
        AuthRes res = authService.update(userId, authReq);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @GetMapping("/find/all")
    public ResponseEntity<List<AuthRes>> findAll() {
        List<AuthRes> resList = authService.findAll();
        return new ResponseEntity<>(resList, HttpStatus.OK);
    }

}
