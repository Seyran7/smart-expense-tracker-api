package com.seyran.expensetracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(
                404,
                "Not Found",
                ex.getMessage(),
                LocalDateTime.now()
        ));

    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(
                400,
                "Bad Request",
                ex.getMessage(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleConflictException(ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiError(
                409,
                "Conflict",
                ex.getMessage(),
                LocalDateTime.now()
        ));
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse>handleValidation(MethodArgumentNotValidException ex){
       Map<String, String> errors = ex.getBindingResult()
               .getFieldErrors()
               .stream()
               .collect(Collectors
               .toMap(FieldError::getField, FieldError::getDefaultMessage,(oldValue, newValue) -> oldValue
       ));
       ValidationErrorResponse response = new ValidationErrorResponse(
               400,
               errors,
               LocalDateTime.now()
       );
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
