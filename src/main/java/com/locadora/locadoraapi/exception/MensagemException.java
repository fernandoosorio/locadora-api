package com.locadora.locadoraapi.exception;

import org.springframework.http.HttpStatus;

public class MensagemException {

    public HttpStatus httpStatus;
    public  String message;

    public MensagemException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return httpStatus;
    }
    
}
