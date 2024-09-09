package com.seven.controllervalidation.exception;

import com.seven.controllervalidation.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.List;
import java.util.Set;

/**
 * 全局异常捕获
 */
@RestControllerAdvice
@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserException.class)
    public Result handlerUserException(UserException e, HttpServletRequest request) {
        String msg = e.getMessage();
        log.error("请求[ {} ] {} 的参数校验发生错误，错误信息：{}", request.getMethod(), request.getRequestURL(), msg, e);
        return Result.error(Result.COMMENT_CODE, msg);
    }

    /**
     * 处理参数异常
     *
     * @param e 参数异常
     * @return 格式化请求结果
     */
    @ExceptionHandler(value = BindException.class)
    public Result handlerBindException(BindException e, HttpServletRequest request) {
        BindingResult bindingResult = e.getBindingResult();
        String validExceptionMsg = getValidExceptionMsg(bindingResult.getAllErrors());
        log.error("请求[ {} ] {} 的参数校验发生错误，错误信息：{}", request.getMethod(), request.getRequestURL(), validExceptionMsg, e);
        return Result.error(Result.COMMENT_CODE, validExceptionMsg);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public Result handlerUnexpectedTypeException(UnexpectedTypeException e, HttpServletRequest request) {
        String msg = e.getMessage();
        log.error("请求[ {} ] {} 的参数校验发生错误，错误信息：{}", request.getMethod(), request.getRequestURL(), msg, e);
        return Result.error(Result.COMMENT_CODE, msg);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result handlerConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        Set<ConstraintViolation<?>> sets = e.getConstraintViolations();
        StringBuilder sb = new StringBuilder();
        if (sets != null && !sets.isEmpty()) {
            sets.forEach(error -> {
                sb.append(error.getPropertyPath().toString()).append(":").append(error.getMessage()).append(";");
            });
        }
        String msg = sb.toString();
        log.error("请求[ {} ] {} 的参数校验发生错误，错误信息：{}", request.getMethod(), request.getRequestURL(), msg, e);
        return Result.error(Result.COMMENT_CODE, msg);
    }

    private String getValidExceptionMsg(List<ObjectError> errors) {
        if (errors != null && !errors.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            errors.forEach(error -> {
                if (error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                    sb.append(fieldError.getField()).append(":");
                }
                sb.append(error.getDefaultMessage()).append(";");
            });
            return sb.toString();
        }
        return null;
    }

    /**
     * 默认全局异常处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return Result.error(Result.COMMENT_CODE, e.getMessage());
    }

}
