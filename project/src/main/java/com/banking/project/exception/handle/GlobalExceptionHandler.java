package com.banking.project.exception.handle;

import com.banking.project.exception.notfound.AccountNotFoundException;
import com.banking.project.exception.notfound.InputNotFoundException;
import com.banking.project.exception.notfound.SafeNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({SafeNotFoundException.class, AccountNotFoundException.class})
    @ResponseStatus(value = NOT_FOUND)
    public Map<String, List<String>> handleInputNotFoundException(final InputNotFoundException exception) {
        return formatErrorsResponse(exception.getMessage());
    }

    private Map<String, List<String>> formatErrorsResponse(final String... errors) {
        return Map.of("Error", Arrays.asList(errors));
    }
}
