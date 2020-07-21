package pl.lenistwo.dragonfox.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.lenistwo.dragonfox.exceptions.BadTokenException;
import pl.lenistwo.dragonfox.exceptions.UserNotFoundException;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler({
            UserNotFoundException.class
    })
    public ResponseEntity handleUserNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({
            BadTokenException.class
    })
    public String handleBadToken() {
        return "redirect:/login";
    }

    @ExceptionHandler({
            BadCredentialsException.class
    })
    public ResponseEntity handleBadCredentials() {
        return new ResponseEntity("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
    }
}
