package ru.finwax.notification.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DeviceNotFoundException.class)
    public ResponseEntity<String> handleDeviceNotFound(DeviceNotFoundException e) {
        log.error("Device not found: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("Validation error: {}", errorMessage);
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception e) {
        log.error("Internal server error: {}", e.getMessage(), e);
        return ResponseEntity.internalServerError().body("Internal server error");
    }
}