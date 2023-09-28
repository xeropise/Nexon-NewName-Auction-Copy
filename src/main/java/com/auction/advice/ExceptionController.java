package com.auction.advice;

import com.auction.common.model.ApiResponse;
import com.auction.common.model.ResponseCode;
import com.auction.user.exception.AccountExistsException;
import com.auction.user.exception.PasswordNotMatchException;
import com.auction.user.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ApiResponse unhandledException(Throwable throwable) {
        log.error("unhandledException message : {}", throwable.getMessage());
        return ApiResponse.fail(ResponseCode.INTERNAL_ERROR, "server internal error");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ObjectError firstError = exception.getBindingResult().getAllErrors().get(0);

        log.error("methodArgumentNotValidException message : {}", firstError.getDefaultMessage());
        return ApiResponse.fail(ResponseCode.BAD_REQUEST, firstError.getDefaultMessage());
    }

    @ExceptionHandler(AccountExistsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ApiResponse accountExistsException(Exception exception) {
        log.error("accountExistsException message : {}", exception.getMessage());
        return ApiResponse.fail(ResponseCode.ACCOUNT_EXISTS, exception.getMessage());
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse passwordNotMatchException(Exception exception) {
        log.error("passwordNotMatchException message : {}", exception.getMessage());
        return ApiResponse.fail(ResponseCode.PASSWORD_NOT_MATCH, exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ApiResponse userNotFoundException(Exception exception) {
        log.error("userNotFoundException message : {}", exception.getMessage());
        return ApiResponse.fail(ResponseCode.ACCOUNT_EXISTS, exception.getMessage());
    }
}
