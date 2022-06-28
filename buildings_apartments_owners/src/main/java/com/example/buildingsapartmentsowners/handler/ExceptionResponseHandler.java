package com.example.buildingsapartmentsowners.handler;

import com.example.buildingsapartmentsowners.dto.HttpResponseDTO;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionResponseHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public HttpResponseDTO handleResponse(ResponseStatusException ex) {
        return HttpResponseDTO.builder()
                .status(ex.getStatus())
                .message(ex.getReason())
                .build();
    }
}