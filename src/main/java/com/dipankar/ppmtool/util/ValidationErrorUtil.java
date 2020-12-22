package com.dipankar.ppmtool.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

public class ValidationErrorUtil {

    public static ResponseEntity<?> validateError(BindingResult result) {
        if(result.hasErrors()) {
            Map<String, String> errorMap =
                    result.getFieldErrors()
                            .stream()
                            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

            return new ResponseEntity(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
