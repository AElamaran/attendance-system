package com.example.backend.Exception;

import com.example.backend.DTO.CommonApiResponse;
import com.example.backend.Exception.InvalidInputException;
import com.example.backend.Exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonApiResponse<String>>  handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity.badRequest().body(new CommonApiResponse<>(400, "Validation Error", errorMessage));
    }
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<CommonApiResponse<String>> handleStudentNotFoundException(StudentNotFoundException ex) {
        return new ResponseEntity<>(new CommonApiResponse<>(404, ex.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<CommonApiResponse<String>> handleInvalidInputException(InvalidInputException ex) {
        return new ResponseEntity<>(new CommonApiResponse<>(400, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonApiResponse<String>> handleGenericException(Exception ex) {
        return new ResponseEntity<>(new CommonApiResponse<>(500, "An unexpected error occurred: " + ex.getMessage(), null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
