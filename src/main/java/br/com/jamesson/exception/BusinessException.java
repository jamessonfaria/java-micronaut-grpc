package br.com.jamesson.exception;

import io.grpc.Status;

public abstract class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public abstract Status getStatus();
}
