package com.fuchangling.exception;

import com.fuchangling.common.api.vo.ResultVO;
import com.fuchangling.enums.ApiStatusCode;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 全局异常处理类
 *
 * @author harlin
 */
@ControllerAdvice
@Slf4j
@ResponseBody
public class ResultExceptionHandler {

    /**
     * 对系统中在使用@Valid校验时产生的异常进行拦截处理
     *
     * @param exception 错误信息集合
     * @return ResultVO
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<Object> validationBodyException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {
            List<String> resultList = Lists.newArrayList();
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError p : errors) {
                FieldError fieldError = (FieldError) p;
                resultList.add(fieldError.getDefaultMessage());
                log.error(fieldError.toString());
            }
            log.info(resultList.toString());
            return ResultVO.error(ApiStatusCode.VAILD_ERROR.getCode(), resultList.toString());
        }
        return ResultVO.error(ApiStatusCode.VAILD_ERROR);
    }

    /**
     * 拦截系统中自定义的异常
     */
    @ExceptionHandler(ResultException.class)
    public ResultVO<Object> resultException(ResultException exception) {
        return ResultVO.error(exception.getCode(), exception.getMessage());
    }

}
