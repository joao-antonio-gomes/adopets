package org.pets.application.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private Map<String, String> errors;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message,
                             Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

}
