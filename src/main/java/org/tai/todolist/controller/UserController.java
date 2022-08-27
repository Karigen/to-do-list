package org.tai.todolist.controller;

import cn.hutool.crypto.digest.Digester;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tai.todolist.entity.JSONResponseEntity;
import org.tai.todolist.entity.User;
import org.tai.todolist.exception.BusinessException;
import org.tai.todolist.exception.ErrorCode;
import org.tai.todolist.service.UserService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Karigen
 * @since 2022-08-09
 */
@RestController
@RequestMapping("/user")
@Api("用户管理")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Digester passwordEncoder;

    @PostMapping("/register")
    @ApiOperation("注册用户")
    public JSONResponseEntity register(@RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       @RequestParam(value = "email", required = false) String email) {
        // 检查用户名是否已经存在
        User check = userService.getOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username));

        if (check != null) {
            throw new BusinessException(ErrorCode.USERNAME_ALREADY_EXIST);
        }

        new User()
                .setUsername(username)
                // 加密用户密码
                .setPassword(passwordEncoder.digestHex(password))
                .setEmail(email)
                .insert();

        return JSONResponseEntity.ok();
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public JSONResponseEntity login(@RequestParam("username") String username,
                                    @RequestParam("password") String password) {
        User check = userService.getOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username));

        if (check == null) {
            throw new BusinessException(ErrorCode.USERNAME_NOT_EXIST);
        }

        if (!check.getPassword().equals(passwordEncoder.digestHex(password))){
            throw new BusinessException(ErrorCode.INCORRECT_PASSWORD);
        }

        return JSONResponseEntity.ok()
                .newData("userid", check.getUserid())
                .newData("username", check.getUsername());
    }

}
