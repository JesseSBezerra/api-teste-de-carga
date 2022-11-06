package br.com.jdsb.movimentoapi.service.exception;

public class MovimentoNaoEncontradoException extends RuntimeException{
    public MovimentoNaoEncontradoException(String message) {
        super(message);
    }
}
