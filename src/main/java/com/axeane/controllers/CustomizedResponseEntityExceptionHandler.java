package com.axeane.controllers;

import com.axeane.utils.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice()
public class CustomizedResponseEntityExceptionHandler implements ProblemHandling {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse badRequest(final ExceptionResponse exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        return error;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse notFoundRequest(final ExceptionResponse exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        return error;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ExceptionResponse handleException(final Exception exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        return error;
    }
}