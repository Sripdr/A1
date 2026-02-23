package in.shop.excep;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorDetails> handleUserNotFound(UserNotFound ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(404).body(errorDetails);
    }

    @ExceptionHandler(UserExist.class)
    public ResponseEntity<ErrorDetails> handleUserExist(UserExist ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(404).body(errorDetails);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),LocalDateTime.now());
        return ResponseEntity.status(500).body(errorDetails);
    }

}
