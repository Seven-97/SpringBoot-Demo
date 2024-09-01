package com.seven.aoplimitrepeatsubmitantishake.exception;

import com.seven.aoplimitrepeatsubmitantishake.constants.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = LimitException.class)
    public Result handlerCommonException(LimitException e, HttpServletRequest request) {
        log.error("请求[ {} ] {} 发生错误，错误信息：{}", request.getMethod(), request.getRequestURL(), e.getMessage(), e);
        return Result.error(Result.ERROR_CODE, e.getMessage());
    }
}
