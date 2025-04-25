package br.com.fillms.projeto_java_ia.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.fillms.projeto_java_ia.exceptions.UserAlreadyExists;
import br.com.fillms.projeto_java_ia.exceptions.UserNotFound;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExists ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }
}
