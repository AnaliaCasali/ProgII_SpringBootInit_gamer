package edu.isp63.prog2.gamer.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /*
     1.  Administra Validation — errores de campos en el request body
     Se dispara cuando un @RequestBody tiene un DTO anotado
     con @Valid y algún campo no pasa las validaciones
      (@NotNull, @NotBlank, @Size, @Email, etc.).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errores = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
        fe ->     errores.put(fe.getField(), fe.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errores);

    }
    /*cuando usamos @Validated a nivel de clase para validar parámetros
     de métodos (@PathVariable, @RequestParam)
      En ese caso falla con ConstraintViolationExceptio*/
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(        ConstraintViolationException ex) {

        Map<String, String> errores = new LinkedHashMap<>();
        ex.getConstraintViolations().forEach(cv ->
                errores.put(cv.getPropertyPath().toString(), cv.getMessage())
        );
        return ResponseEntity.badRequest().body(errores);
    }

    /*2. Administra NotFound — entidad no encontrada en la base de datos
    * cuando en el service busco por un id y este no se encuentra lanza
    *  exception ResourceNotFoundException por ejemplo
    *   JugadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Jugador no encontrado con id: " + id));*/

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }
        /* 4. cualquier otro error lo administro aqui*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Error inesperado: " + ex.getMessage()));
    }

    public record ErrorResponse(String message) { }
}
