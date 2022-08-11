package org.tai.todolist.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Karigen B
 * @create 2022-08-11 10:39
 */
@Configuration
@MapperScan("org.tai.todolist.dao")
public class DAOConfig {
}
