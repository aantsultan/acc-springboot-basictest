package com.springboot.basictest.userservice.exception;

import com.springboot.basictest.userservice.util.ExceptionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomizeExceptionHandler extends ResponseStatusExceptionHandler {

    @ExceptionHandler(InternalServerException.class)
    public final ResponseEntity<ErrorResponse> handleInternalException(Exception ex, WebRequest request) throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add(ex.getMessage());
        ErrorResponse errorResponse =
                new ErrorResponse(
                        ExceptionEnum.SYSTEM_ERROR.getDescription(),
                        ExceptionEnum.SYSTEM_ERROR.getCode(),
                        messages
                );
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidDataExecption.class)
    public final ResponseEntity<ErrorResponse> handleInvalidException(Exception ex, WebRequest request) throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add(ex.getMessage());
        ErrorResponse errorResponse =
                new ErrorResponse(
                        ExceptionEnum.BAD_REQUEST.getDescription(),
                        ExceptionEnum.BAD_REQUEST.getCode(),
                        messages
                );
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
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
