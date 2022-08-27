package org.tai.todolist.config;

import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.MD5;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Karigen B
 * @create 2022-08-09 14:12
 */
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    @Bean
    public Digester passwordEncoder(){
        return MD5.create();
    }

}
