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
    public JSONResponseEntity getFans(@RequestParam("userId") Integer userid) {
       // List<Map<Integer, Object>> fans = fansService.selectByUserId(userid);
        List<Fans> fans = fansService.lambdaQuery()
                .eq(Fans::getUserid, userid)
                .list();
        int count = fans.size();
        return JSONResponseEntity.ok()
                .newData("fans", fans)
                .newData("count", count);
    }

    @PostMapping("/getfollows")
    @ApiOperation("获取用户所有关注")
    public JSONResponseEntity getFollows(@RequestParam("fanId") Integer fanid) {
        //List<Map<Integer, Object>> follows = fansService.selectByFanId(fanid);
        List<Fans> follows = fansService.lambdaQuery()
                .eq(Fans::getFanId, fanid)
                .list();
        int count = follows.size();
        return JSONResponseEntity.ok()
                .newData("follows", follows)
                .newData("count", count);
    }

    @PostMapping("/commonfollows")
    @ApiOperation("获取两个用户的共同关注")
    public JSONResponseEntity getCommonFollows(@RequestParam("fan1Id") Integer fan1Id, @RequestParam("fan2Id") Integer fan2Id) {
     List<Map<Integer,Object>> commonfollows=  fansService.selectCommonFollows(fan1Id,fan2Id);
        int count = commonfollows.size();
        return JSONResponseEntity.ok()
                .newData("commonfollows", commonfollows)
                .newData("count", count);
    }

    @PostMapping("/mutualfollows")
    @ApiOperation("获取用户的互相关注")
    public JSONResponseEntity getMutualFollows(@RequestParam("fanId") Integer fanId) {
        List<Map<Integer,Object>> mutualfollows=  fansService.selectMutualFollows(fanId);
        int count = mutualfollows.size();
        return JSONResponseEntity.ok()
                .newData("mutualfollows", mutualfollows)
                .newData("count", count);
    }

    @PostMapping("/delete")
    @ApiOperation("取消关注")
    public JSONResponseEntity deleteFan(@RequestParam("fanId") Integer fanId, @RequestParam("userId") Integer userId) {
        fansService.lambdaUpdate()
                .eq(Fans::getFanId, fanId)
                .eq(Fans::getUserid, userId)
                .remove();
        return JSONResponseEntity.ok();
    }
}
