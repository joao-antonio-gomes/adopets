package org.pets.infrastructure.config;

import lombok.Data;
import org.pets.application.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiExceptionDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        ex.getBindingResult()
          .getAllErrors()
          .forEach(error -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();

              if (errorMessage != null) {
                  errors.putIfAbsent(fieldName, List.of(errorMessage));
              } else {
                  errors.putIfAbsent(fieldName, List.of("Invalid value"));
              }
          });

        final ApiExceptionDTO apiExceptionDTO = new ApiExceptionDTO("Invalid values",
                                                                    errors,
                                                                    HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiExceptionDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiExceptionDTO> handleBusinessException(BusinessException ex) {
        final ApiExceptionDTO apiExceptionDTO = new ApiExceptionDTO(ex.getMessage(),
                                                                    ex.getErrors(),
                                                                    HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiExceptionDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Data
    public class ApiExceptionDTO {
        private final String error;
        private final Map<String, ?> errors;
        private final Integer status;

        public ApiExceptionDTO(String error, Map<String, ?> errors, Integer status) {
            this.error = error;
            this.errors = errors;
            this.status = status;
        }
    }
}
