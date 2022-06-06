package br.com.jamesson.exception;

import io.grpc.Status;

public class InvalidArgumentException extends BusinessException{
    public InvalidArgumentException(String message) {
        super(message);
    }

    @Override
    public Status getStatus() {
        return Status.INVALID_ARGUMENT;
    }
}
