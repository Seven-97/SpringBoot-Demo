package com.seven.controllervalidation.exception;

import com.seven.controllervalidation.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常捕获
 */
@RestControllerAdvice
@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    /**
     * 处理参数异常
     *
     * @param ex 参数异常
     * @return 格式化请求结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult exceptions = ex.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                for (ObjectError error : errors) {
                    log.warn("参数请求异常：{}", error.getDefaultMessage());
                }
                FieldError fieldError = (FieldError) errors.get(0);
                return Result.error(fieldError.getCode(), fieldError.getField() + fieldError.getDefaultMessage());
            }
        }
        log.error("运行出错", ex);
        return Result.error(Result.COMMENT_CODE, Result.RUNNING_ERROR_MSG);
    }

}
