package ravi.learning.productcatalogservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ravi.learning.productcatalogservice.dto.ErrorResponseDto;
import ravi.learning.productcatalogservice.exceptions.NotFoundException;

@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(Exception exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorMessage(exception.getMessage());

        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
