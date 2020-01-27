package org.lizhishu.love.config;


import com.alibaba.fastjson.JSON;
import org.lizhishu.love.core.ResultGenerator;
import org.lizhishu.love.core.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sleo
 * @date 2017/10/12
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity systemExceptionHandler(Exception ex) {
        return new ResponseEntity(ResultGenerator.genFailResult(ex.getMessage()), HttpStatus.OK);
    }


    /**
     * 添加springboot @Valid 保存转标准信息处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return new ResponseEntity(ResultGenerator.genFailResult(JSON.toJSONString(errors)), HttpStatus.OK);
    }
}
