package com.portops.auth_service.exceptions;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.portops.auth_service.response.ApiResponse;

import jakarta.validation.ConstraintViolationException;


@ControllerAdvice 
public class GlobalExceptionHandler {

    // Maneja errores cuando un recurso NO existe (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(ResourceNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(
                        ex.getMessage(),                 // Mensaje de la excepción
                        HttpStatus.NOT_FOUND.value()     // Código 404
                ));
    }

    // Maneja errores cuando un recurso ya existe (409)
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleConflict(ResourceAlreadyExistsException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(
                        ex.getMessage(),
                        HttpStatus.CONFLICT.value()      // Código 409
                ));
    }

    // Maneja errores de datos inválidos enviados por el cliente (400)
    //Se usa cuando el cliente envía datos incorrectos o incompletos
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(BadRequestException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST.value()   // Código 400
                ));
    }

    //Se dispara automáticamente cuando fallan validaciones del DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        return ResponseEntity.badRequest().body(
                ApiResponse.error("Validation error", 400)
        );
    }

    //Validation (@RequestParam / @PathVariable) Se usa cuando validas parámetros directamente.
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleConstraintViolation(ConstraintViolationException ex) {


        return ResponseEntity.badRequest().body(
                ApiResponse.error("Validation error", 400)
        );
    }

    // Maneja cualquier error NO controlado (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneral(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(
                        "Unexpected error: " + ex.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value() // Código 500
                ));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<Void>> handleUnauthorized(UnauthorizedException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error(ex.getMessage(), 401));
        }
}
