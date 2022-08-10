package org.tai.todolist.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tai.todolist.entity.JSONResponseEntity;
import org.tai.todolist.exception.BusinessException;

/**
 * @author Karigen B
 * @create 2022-07-29 1:38
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public JSONResponseEntity exception(Exception exception) {
        if (exception instanceof BusinessException businessException) {
            return JSONResponseEntity.error(businessException);
        } else {
            return JSONResponseEntity.error("A0001 ", exception.getMessage());
        }
    }

}
