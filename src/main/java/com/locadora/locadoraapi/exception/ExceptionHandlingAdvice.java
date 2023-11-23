package com.locadora.locadoraapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler(VeiculoJaCadastrado.class)
    public ResponseEntity<MensagemException> VeiculoJaCadastrado(Exception ex) {
        return handleException( new MensagemException(HttpStatus.CONFLICT,ex.getLocalizedMessage()) );
    }

    @ExceptionHandler(VeiculoNaoAlugado.class)
    public ResponseEntity<MensagemException> VeiculoNaoAlugado(Exception ex) {
        return handleException( new MensagemException(HttpStatus.NOT_FOUND,ex.getLocalizedMessage()) );
    }

    @ExceptionHandler(ClienteJaCadastrado.class)
    public ResponseEntity<MensagemException> ClienteJaCadastrado(Exception ex) {
        return handleException( new MensagemException(HttpStatus.CONFLICT,ex.getLocalizedMessage()) );
    }

    @ExceptionHandler(ClienteNaoCadastrado.class)
    public ResponseEntity<MensagemException> ClienteNaoCadastrado(Exception ex) {
        return handleException( new MensagemException(HttpStatus.NOT_FOUND,ex.getLocalizedMessage()) );
    }



    private ResponseEntity<MensagemException> handleException( MensagemException mensagemException) {
        return new ResponseEntity<>( mensagemException , mensagemException.getStatus() );
    }
    
}
