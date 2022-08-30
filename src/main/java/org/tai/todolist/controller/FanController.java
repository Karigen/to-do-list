package org.tai.todolist.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tai.todolist.entity.Fans;
import org.tai.todolist.entity.JSONResponseEntity;
import org.tai.todolist.entity.Task;
import org.tai.todolist.entity.User;
import org.tai.todolist.service.FansService;
import org.tai.todolist.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Antonio
 * @since 2022-08-09
 */
@RestController
@RequestMapping("/fan")
public class FanController {
    @Autowired
    private FansService fansService;

    @Autowired
    private UserService userService;

    @PostMapping("/getfans")
    @ApiOperation("获取用户所有粉丝")
    public JSONResponseEntity getfans(@RequestParam("userId") Integer userid) {
        List<Map<Integer, Object>> fans = fansService.selectByUserId(userid);
        int count = fans.size();
        return JSONResponseEntity.ok()
                .newData("fans", fans)
                .newData("count", count);
    }

    @PostMapping("/getfollows")
    @ApiOperation("获取用户所有关注")
    public JSONResponseEntity getfollows(@RequestParam("fanId") Integer fanid) {
        List<Map<Integer, Object>> follows = fansService.selectByFanId(fanid);
        int count = follows.size();
        return JSONResponseEntity.ok()
                .newData("follows", follows)
                .newData("count", count);
    }
}
