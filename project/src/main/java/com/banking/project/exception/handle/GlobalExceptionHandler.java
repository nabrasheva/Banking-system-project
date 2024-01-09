package com.banking.project.exception.handle;

import com.banking.project.exception.exists.InputAlreadyExistsException;
import com.banking.project.exception.exists.SafeAlreadyExistsException;
import com.banking.project.exception.exists.UserAlreadyExistsException;
import com.banking.project.exception.notfound.*;
import com.banking.project.exception.validation.InvalidInputException;
import com.banking.project.exception.validation.NotEnoughFundsException;
import com.banking.project.exception.validation.WrongCreditAmountInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.banking.project.constant.ExceptionMessages.CAUGHT_EXCEPTION;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({SafeNotFoundException.class, AccountNotFoundException.class, TransactionNotFoundException.class, UserNotFoundException.class})
    @ResponseStatus(value = NOT_FOUND)
    public Map<String, List<String>> handleInputNotFoundException(final InputNotFoundException exception) {
        log.error(CAUGHT_EXCEPTION, exception);
        return formatErrorsResponse(exception.getMessage());
    }

    @ExceptionHandler({SafeAlreadyExistsException.class, UserAlreadyExistsException.class})
    @ResponseStatus(value = CONFLICT)
    public Map<String, List<String>> handleInputAlreadyExistsException(final InputAlreadyExistsException exception) {
        log.error(CAUGHT_EXCEPTION, exception);
        return formatErrorsResponse(exception.getMessage());
    }

    @ExceptionHandler({NotEnoughFundsException.class, WrongCreditAmountInputException.class})
    @ResponseStatus(value = BAD_REQUEST)
    public Map<String, List<String>> handleInvalidInputException(final InvalidInputException exception) {
        log.error(CAUGHT_EXCEPTION, exception);
        return formatErrorsResponse(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = BAD_REQUEST)
    public Map<String, List<String>> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {

        log.error(CAUGHT_EXCEPTION, exception);

        final List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return formatErrorsResponse(String.valueOf(errors));
    }


    private Map<String, List<String>> formatErrorsResponse(final String... errors) {
        return Map.of("error", Arrays.asList(errors));
    }
}
