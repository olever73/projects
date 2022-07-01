package com.example.buildingsapartmentsowners.handler;

import com.example.buildingsapartmentsowners.dto.HttpResponseDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;



@RestControllerAdvice
public class GeneralExceptionResponseHandler {

        @ExceptionHandler(ResponseStatusException.class)
        public ResponseEntity<HttpResponseDTO> handleResponse(ResponseStatusException ex) {
            return ResponseEntity
                    .status(ex.getStatus())
                    .body(
                            HttpResponseDTO
                                    .builder()
                                    .status(ex.getStatus())
                                    .message(ex.getReason())
                                    .build()
                    );
        }

}