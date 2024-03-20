package com.abreu.todoHexagonal.api.controller.exception;

import com.abreu.todoHexagonal.api.controller.CustomExceptionHandler;
import com.abreu.todoHexagonal.api.dto.ErrorResponse;
import com.abreu.todoHexagonal.business.exception.BadRequestException;
import com.abreu.todoHexagonal.business.exception.IdNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomExceptionHandlerTest {

    @InjectMocks
    CustomExceptionHandler customExceptionHandler;

    @Test
    void shouldReturnBadRequestException() {
        BadRequestException exception = new BadRequestException("Id not found");

        ResponseEntity<ErrorResponse> errorResponseEntity = customExceptionHandler.handleBadRequestException(exception);
        ErrorResponse errorResponse = errorResponseEntity.getBody();

        assert errorResponse != null;
        assert errorResponse.message().equals("Id not found");
        assert errorResponse.status() == 400;

        assertEquals(400, errorResponseEntity.getStatusCode().value());
    }

    @Test
    void shouldReturnIdNotFoundException() {
        IdNotFoundException exception = new IdNotFoundException("Id not found");

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getRequestURI()).thenReturn("/test-uri");

        ResponseEntity<ErrorResponse> errorResponseEntity = customExceptionHandler.handleIdNotFoundException(exception, request);
        ErrorResponse errorResponse = errorResponseEntity.getBody();

        assertNotNull(errorResponse, "ErrorResponse is null");

        assertEquals("Id not found", errorResponse.message());
        assertEquals(404, errorResponse.status());

        assertEquals(404, errorResponseEntity.getStatusCode().value());
    }

    @Test
    void shouldHandleMethodArgumentNotValidException() {
        // Arrange
        CustomExceptionHandler handler = new CustomExceptionHandler();
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");

        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));
        when(bindingResult.getObjectName()).thenReturn("objectName");

        // Act
        ResponseEntity<ErrorResponse> response = handler.handleMethodArgumentNotValidException(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorResponse errorResponse = response.getBody();
        assert errorResponse != null;
        assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.status());
        assertEquals("objectName", errorResponse.path());
        assertEquals(List.of("defaultMessage"), errorResponse.errors());
    }
}
