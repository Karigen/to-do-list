package org.tai.todolist.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Karigen B
 * @create 2022-08-11 10:15
 */
@Component
@Aspect
@Slf4j
public class LogAop {

    @AfterThrowing("execution(* org.tai.todolist.controller.ExceptionController.exception(..))")
    public void exceptionLog() {
        log.error("发生异常");
    }

}
