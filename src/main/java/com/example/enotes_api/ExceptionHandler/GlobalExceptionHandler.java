package com.example.enotes_api.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
 
    // @ExceptionHandler(NullPointerException.class)
    // public ResponseEntity<?> handleNullPointerException(Exception e){
    //     return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ObjectError> allerrors = e.getBindingResult().getAllErrors();

        Map<String,Object> error = new LinkedHashMap<>(); 
        allerrors.stream().forEach(er->{
                String ms = er.getDefaultMessage();
                String field = ((FieldError)(er)).getField();
                error.put(field, ms);
        });
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException e){
        return new ResponseEntity<>(e.getError(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistDataException.class)
    public ResponseEntity<?> handleExistDataException(ExistDataException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
    }
}

