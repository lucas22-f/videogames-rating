package com.gamesrating.gamesratingdemo.security;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("${server.error.path:/error}")
public class CustomErrorController implements ErrorController {

    @RequestMapping
    public ResponseEntity<Map<String, Object>> handleErrors(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = status != null ? Integer.parseInt(status.toString()) : 500;

        String message = switch (statusCode) {
            case 404 ->
                "Ruta no encontrada";
            case 401 ->
                "No autorizado";
            case 403 ->
                "Error jwt incompatible o inexistente";
            default ->
                "Error inesperado";
        };

        return ResponseEntity.status(statusCode).body(Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "status", statusCode,
                "message", message
        ));
    }

}
