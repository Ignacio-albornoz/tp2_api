package com.neoris.turnosrotativos.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("isSuccess", false);

        List<String> menssageErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        responseBody.put("message", menssageErrors);

        return new ResponseEntity<>(responseBody, headers, status);
    }

    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<Object> handleBussinessException(
            BussinessException ex
    ) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        switch (ex.getStatus()){
            case 409:
                responseBody.put("isSuccess", false);
                responseBody.put("message", ex.getMessage());
                return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
            case 400:
                responseBody.put("isSuccess", false);
                responseBody.put("message", ex.getMessage());
                return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);

            case 404:
                responseBody.put("isSuccess", false);
                responseBody.put("message", ex.getMessage());
                return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);

            default:
                responseBody.put("isSuccess", false);
                responseBody.put("message", "Ocurrio un error inesperado.");
                return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);


        }

    }

}
