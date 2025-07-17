package org.example.digitalbanking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BalanceNotSuffisendException.class)
    public ResponseEntity<Map<String, String>> handleBalanceException(BalanceNotSuffisendException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage()); // ✅ ex.getMessage() = "solde inssufisant"
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
