package org.tai.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tai.todolist.entity.Blog;
import org.tai.todolist.entity.Fans;
import org.tai.todolist.entity.Task;
import org.tai.todolist.entity.User;

/**
 * @author Karigen B
 * @create 2022-08-22 19:06
 */
@RestController
@RequestMapping("/entity")
public class SwaggerController {

    @GetMapping("/Blog")
    public Blog blog() {
        return new Blog();
    }

    @GetMapping("/Fans")
    public Fans fans() {
        return new Fans();
    }

    @GetMapping("/Task")
    public Task task() {
        return new Task();
    }

    @GetMapping("/User")
    public User user() {
        return new User();
    }

}
