package com.example.tasker.exception;

import lombok.Getter;

@Getter
public class ApiValidationError extends ApiSubError {

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    public ApiValidationError(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
