package com.thomaskint.library.domain;

public class DomainException extends Exception {
    private ErrorCode code;

    public enum ErrorCode {
        LOAN_NOT_FOUND,
        EXISTING_LOAN,
        BOOK_NOT_FOUND,
    }

    public DomainException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
