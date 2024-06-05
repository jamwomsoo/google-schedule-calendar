package com.larry.fc.finalproject.api.exception;

import com.larry.fc.finalproject.core.exception.CalendarException;
import com.larry.fc.finalproject.core.exception.ErrorCode;
import lombok.Data;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {
    // error code ENUM에 httpstatus를 같이 안 쓰는이유는 다른 모듈(CORE)에서 발생시 처리 불가
    @ExceptionHandler(CalendarException.class)
    public ResponseEntity<ErrorResponse> handle(CalendarException ex){
        final ErrorCode errorCode = ex.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(errorCode, errorCode.getMessage()), ErrorHttpStatusMapper.mapToStatus(errorCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex){
        final ErrorCode errorCode = ErrorCode.VALIDATION_FAIL;
        return new ResponseEntity<>(
                new ErrorResponse(
                        errorCode,
                        Optional.ofNullable(ex.getBindingResult().getFieldError())//ex의 필드에러를 뽑아냄
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)//위의 필드에러에서 디폴트 에러메세지를 뽑아냄
                                .orElse(errorCode.getMessage())//
                ), ErrorHttpStatusMapper.mapToStatus(errorCode));
    }


    @Data
    public static class ErrorResponse{
        private final ErrorCode errorCode;
        private final String errorMessage;
    }
}
