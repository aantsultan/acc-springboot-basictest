package com.springboot.basictest.userservice.exception;

import com.springboot.basictest.userservice.util.ExceptionEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class CustomizeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleInternalException(Exception ex, WebRequest request) throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add(ex.getMessage());
        messages.add("System error, we're unable to process your request at the moment");
        ErrorResponse errorResponse =
                new ErrorResponse(
                        ExceptionEnum.SYSTEM_ERROR.getDescription(),
                        ExceptionEnum.SYSTEM_ERROR.getCode(),
                        messages
                );
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        Set<String> messagesSet = new HashSet<>();
        for(ObjectError error : errorList){
            messagesSet.add(error.getDefaultMessage());
        }
        List<String> messages = new ArrayList<>(messagesSet);
        ErrorResponse errorResponse = new ErrorResponse(
                ExceptionEnum.BAD_REQUEST.getDescription(),
                ExceptionEnum.BAD_REQUEST.getCode(),
                messages
                );
        return new ResponseEntity(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public final ResponseEntity<ErrorResponse> handleAlreadyExistException(Exception ex, WebRequest request) throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add(ex.getMessage());
        ErrorResponse errorResponse =
                new ErrorResponse(
                        ExceptionEnum.ALREADY_EXIST.getDescription(),
                        ExceptionEnum.ALREADY_EXIST.getCode(),
                        messages
                );
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleNotFoundException(Exception ex, WebRequest request) throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add(ex.getMessage());
        ErrorResponse errorResponse =
                new ErrorResponse(
                        ExceptionEnum.NOT_FOUND.getDescription(),
                        ExceptionEnum.NOT_FOUND.getCode(),
                        messages
                );
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
