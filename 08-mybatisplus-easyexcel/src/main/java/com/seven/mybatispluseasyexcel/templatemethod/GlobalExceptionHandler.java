package com.seven.mybatispluseasyexcel.templatemethod;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = ExcelValidatorException.class)
    public Result handlerEasyExcelValidatorException(ExcelValidatorException e, HttpServletRequest request) {
        String message = e.getMessage();
        log.error("请求[ {} ] {} 的校验发生错误，错误信息：{}", request.getMethod(), request.getRequestURL(), message, e);
        return Result.error(TaskResultConstants.IMPORT_FAIL.getCode(), message);
    }

}
