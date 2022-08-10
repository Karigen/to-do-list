package org.tai.todolist.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tai.todolist.entity.JSONResponseEntity;
import org.tai.todolist.exception.BusinessException;

import java.util.Map;

/**
 * @author Karigen B
 * @create 2022-07-29 1:38
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public Map<String, Object> exception(Exception exception) {
        return Map.of("message", exception.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public JSONResponseEntity businessException(BusinessException businessException) {
        return JSONResponseEntity.error(businessException);
    }

}
