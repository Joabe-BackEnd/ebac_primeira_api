package com.homework.ebac.dev.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProdutosExceptionHandler {

    @ExceptionHandler(ProdutosException.class)
    public ResponseEntity<String> handlerBadRequest(ProdutosException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
