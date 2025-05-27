package com.gamesrating.gamesratingdemo.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

// Rest Controller advise es una clase la cual maneja los errores globalmente 
// Donde podemos encontrarlos manejarlos y setear los errores con sus mensajes respectivos. 
// en este caso lo utilizamos para manejar los errores de validacion que vienen de login y register respectivamente
@RestControllerAdvice
public class CustomErrorMessagesHandler {

    //tenemos que agregar la anotacion exeptionHandler con la clase que sabemos que va a fallar 
    // en este caso MethodArgumentNotValidExeption.class
    @ExceptionHandler(MethodArgumentNotValidException.class)

    // dentro ya del metodo creamos la funcion que retorna un ResponseEntity con un par clave valor String String simulando el json
    // luego la funcion resive el MethodArgumentNotValidException
    public ResponseEntity<Map<String, String>> handleValidator(MethodArgumentNotValidException manve) {

        //creamos el map de errores nuevo vacio
        Map<String, String> errores = new HashMap<>();

        //dentro de manve existe el metodo getBindingResult 
        // y luego podemos acceder a get Fielderrors para acceder a los errores 
        // y al final con el foreach vamos agregando los campos de errores que hayan fallado y devolvemos su mensaje por defecto.
        manve.getBindingResult().getFieldErrors().forEach(el -> {
            errores.put(el.getField(), el.getDefaultMessage());
        });

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleErrorEntityNotFound(EntityNotFoundException enfe) {
        Map<String, String> errores = new HashMap<>();
        errores.put("error", enfe.getLocalizedMessage());
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
