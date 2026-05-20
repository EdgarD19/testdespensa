package com.proyecto2.DespensaProyect.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.joining("; "));
        if (msg.isEmpty()) {
            msg = "Validación fallida";
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body(msg));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : "Petición inválida";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body(msg));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalState(IllegalStateException ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : "Estado inválido";
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body(msg));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDenied(AccessDeniedException ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : "Acceso denegado";
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body(msg));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : "Error interno";
        String lower = msg.toLowerCase();

        if (lower.contains("no se puede")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(body(msg));
        }
        if (lower.contains("no encontrado")
                || lower.contains("no encontrada")
                || lower.contains("emncontrada")
                || lower.contains("invalido")
                || lower.contains("inválido")
                || lower.contains("no disponible")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body(msg));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body(msg));
    }

    private static Map<String, Object> body(String message) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("message", message);
        m.put("detail", message);
        return m;
    }
}
