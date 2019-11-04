package com.example.demo.aspect;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liufei
 * @since 2019-10-31 17:54
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数校验异常针对@RequestBody
     * @param e 异常对象
     * @return 校验不通过信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage).collect(Collectors.toList());
    }

    /**
     * 参数校验异常针对@PathVariable和@RequestParam
     * @param e 异常对象
     * @return 校验不通过信息
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public List<String> handlerConstraintViolationException(ConstraintViolationException e) {
        return e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }
}
