package com.wz.poc.exception;

import com.google.common.collect.Lists;
import com.wz.poc.model.constant.StateCode;
import com.wz.poc.model.vo.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;


@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler({MyException.class})
    @ResponseBody
    public ResultWrapper<String> processQCodeException(final MyException e) {
        log.error("出现异常:{}", e.getMessage());
        return ResultWrapper.<String>builder().code(e.getCode().getCode())
                .data(null).msg(e.getLocalizedMessage()).build();
    }


    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultWrapper<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("出现异常:{}", e.getMessage());
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        // 已经设置了快速失败，可以默认取首个错误即可
        List<String> errorArr = Lists.newArrayList();
        for (ObjectError error : errors) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorArr.add(fieldError.getField() + fieldError.getDefaultMessage());
            } else {
                errorArr.add(error.getObjectName() + error.getDefaultMessage());
            }
        }
        String errMsg = String.join(";", errorArr.toArray(new String[]{}));
        return ResultWrapper.<String>builder().code(StateCode.METHOD_ARGUMENT_NOT_VALID.code()).msg(errMsg).build();
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultWrapper<String> constraintViolationException(ConstraintViolationException e) {
        // 拦截单个参数校验异常捕获
        log.error("出现异常:{}", e.getMessage());
        List<String> errorArr = Lists.newArrayList();
        for (ConstraintViolation constraint : e.getConstraintViolations()) {
            errorArr.add(constraint.getPropertyPath() + "非法 " + constraint.getMessage());
        }
        String errMsg = String.join(";", errorArr.toArray(new String[]{}));
        return ResultWrapper.<String>builder().code(StateCode.CONSTRAINT_VIOLATION.code()).msg(errMsg).build();
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ResultWrapper<String> bindException(BindException e) {
        // 拦截单个参数校验异常捕获
        log.error("出现异常:{}", e.getMessage());
        // @RequestParam 参数校验失败
        List<String> errorArr = Lists.newArrayList();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorArr.add(fieldError.getField() + "非法 " + fieldError.getDefaultMessage());
        }
        String errMsg = String.join(";", errorArr.toArray(new String[]{}));
        return ResultWrapper.<String>builder().code(StateCode.CONSTRAINT_VIOLATION.code()).msg(errMsg).build();
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultWrapper<String> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("出现异常:{}", e.getMessage());
        String errMsg = e.getParameterName() + "非法:" + e.getMessage();
        return ResultWrapper.<String>builder().code(StateCode.ERROR.code()).msg(errMsg).build();
    }

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ResultWrapper<String> exception(Throwable e) {
        log.error("出现异常", e);
        return ResultWrapper.<String>builder().code(StateCode.ERROR.code()).msg("服务器正忙，请稍后再试").build();
    }


    /**
     * 参数错误类型转换
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResultWrapper<String> parameterTypeConvertException(HttpMessageConversionException e) {
        log.error("出现异常", e);
        return ResultWrapper.<String>builder().code(StateCode.ERROR.code()).msg(e.getMessage()).build();
    }

    /**
     * 参数错误类型转换
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultWrapper<String> methodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("出现异常:{}", e.getMessage());
        return ResultWrapper.<String>builder().code(StateCode.ERROR.code()).msg(e.getMessage()).build();
    }
}
