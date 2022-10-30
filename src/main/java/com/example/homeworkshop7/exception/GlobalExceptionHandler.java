package com.example.homeworkshop7.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public JsonErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new JsonErrorResponse("Validation error", errors);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public JsonErrorResponse handleNotFoundException(NotFoundException exception) {
        return new JsonErrorResponse(exception.getMessage());
    }
}
