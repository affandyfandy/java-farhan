package com.example.fpt_midterm_pos.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

class GlobalExceptionHandlerTest {

    private static final String VALIDATION_ERROR_MESSAGE = "Validation error";
    private static final String INVALID_ARGUMENT_MESSAGE = "Invalid argument";
    private static final String GENERIC_ERROR_MESSAGE = "Generic error";
    private static final String IO_ERROR_MESSAGE = "IO error";
    private static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource not found";
    private static final String BAD_REQUEST_MESSAGE = "Bad request";

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void testHandleValidationErrors() {
        FieldError fieldError = mock(FieldError.class);
        when(fieldError.getDefaultMessage()).thenReturn(VALIDATION_ERROR_MESSAGE);

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<Map<String, List<String>>> response = exceptionHandler.handleValidationErrors(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(List.of(VALIDATION_ERROR_MESSAGE), response.getBody().get("errors"));
    }

    @Test
    void testHandleIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException(INVALID_ARGUMENT_MESSAGE);

        ResponseEntity<Map<String, String>> response = exceptionHandler.handleIllegalArgumentException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(INVALID_ARGUMENT_MESSAGE, response.getBody().get("error"));
    }

    @Test
    void testHandleException() {
        Exception ex = new Exception(GENERIC_ERROR_MESSAGE);

        ResponseEntity<Map<String, String>> response = exceptionHandler.handleException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(GENERIC_ERROR_MESSAGE, response.getBody().get("error"));
    }

    @Test
    void testHandleIOException() {
        IOException ex = new IOException(IO_ERROR_MESSAGE);

        ResponseEntity<Map<String, String>> response = exceptionHandler.handleIOException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(IO_ERROR_MESSAGE, response.getBody().get("error"));
    }

    @Test
    void testHandleResourceNotFoundException() {
        ResourceNotFoundException ex = new ResourceNotFoundException(RESOURCE_NOT_FOUND_MESSAGE);

        ResponseEntity<Map<String, String>> response = exceptionHandler.handleResourceNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(RESOURCE_NOT_FOUND_MESSAGE, response.getBody().get("error"));
    }

    @Test
    void testHandleBadRequestException() {
        BadRequestException ex = new BadRequestException(BAD_REQUEST_MESSAGE);

        ResponseEntity<Map<String, String>> response = exceptionHandler.handleBadRequestException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(BAD_REQUEST_MESSAGE, response.getBody().get("error"));
    }
}
