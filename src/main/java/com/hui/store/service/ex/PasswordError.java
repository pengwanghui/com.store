package com.hui.store.service.ex;

public class PasswordError extends ServiceException{
    public PasswordError() {
        super();
    }

    public PasswordError(String message) {
        super(message);
    }

    public PasswordError(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordError(Throwable cause) {
        super(cause);
    }

    protected PasswordError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
