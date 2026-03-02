package in.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFound(UserNotFoundException ex) {
        ErrorDetails details = new ErrorDetails(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNameNotFound(UsernameNotFoundException ex){
        ErrorDetails details = new ErrorDetails(LocalDateTime.now(), ex.getMessage());
        return  new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleUserExistException(UserExistException ex) {
        ErrorDetails details = new ErrorDetails(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(details, HttpStatus.CONFLICT);
    }

    public ResponseEntity<ErrorDetails> handleBadCredentialsExc(BadCredentialsException ex) {
        ErrorDetails details = new ErrorDetails(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(details, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception ex) {
        ErrorDetails details = new ErrorDetails(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(details,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
