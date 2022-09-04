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
import org.tai.todolist.exception.BusinessException;
import org.tai.todolist.exception.ErrorCode;
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
@CrossOrigin
public class FanController {
    @Autowired
    private FansService fansService;

    @Autowired
    private UserService userService;


    //fans数组参数 fanId fanName followed(bool)
    @PostMapping("/getfans")
    @ApiOperation("获取用户所有粉丝")
    public JSONResponseEntity getFans(@RequestParam("userId") Integer userid) {
        List<Map<Integer, Object>> fans = fansService.selectByUserId(userid);
        return JSONResponseEntity.ok()
                .newData("fans", fans);
    }

    //follows数组参数 followId followName
    @PostMapping("/getfollows")
    @ApiOperation("获取用户所有关注")
    public JSONResponseEntity getFollows(@RequestParam("fanId") Integer fanid) {
        List<Map<Integer, Object>> follows = fansService.selectByFanId(fanid);
        return JSONResponseEntity.ok()
                .newData("follows", follows);
    }

    @PostMapping("/commonfollows")
    @ApiOperation("获取两个用户的共同关注")
    public JSONResponseEntity getCommonFollows(@RequestParam("fan1Id") Integer fan1Id, @RequestParam("fan2Id") Integer fan2Id) {
        List<Map<Integer, Object>> commonfollows = fansService.selectCommonFollows(fan1Id, fan2Id);

        return JSONResponseEntity.ok()
                .newData("commonfollows", commonfollows);
    }

    @PostMapping("/mutualfollows")
    @ApiOperation("获取用户的互相关注")
    public JSONResponseEntity getMutualFollows(@RequestParam("fanId") Integer fanId) {
        List<Map<Integer, Object>> mutualfollows = fansService.selectMutualFollows(fanId);

        return JSONResponseEntity.ok()
                .newData("mutualfollows", mutualfollows);
    }

    //更新后的fans & follows
    @PostMapping("/delete")
    @ApiOperation("取消关注")
    public JSONResponseEntity deleteFan(@RequestParam("fanId") Integer fanId, @RequestParam("userId") Integer userId) {
        fansService.lambdaUpdate()
                .eq(Fans::getFanId, fanId)
                .eq(Fans::getUserid, userId)
                .remove();

        List<Map<Integer, Object>> fans = fansService.selectByUserId(userId);
        List<Map<Integer, Object>> follows = fansService.selectByFanId(userId);
        return JSONResponseEntity.ok()
                .newData("fans", fans)
                .newData("follows", follows);
    }

    //fans数组
    @PostMapping("/follow")
    @ApiOperation("查询用户并follow")
    public JSONResponseEntity users(@RequestParam("myuserid") Integer myUserid,
                                    @RequestParam("searchingusername") String searchingUsername) {
        User follow = userService.getOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, searchingUsername));

        if (follow == null) {
            throw new BusinessException(ErrorCode.USERNAME_NOT_EXIST);
        }

        new Fans()
                .setUserid(follow.getUserid())
                .setFanId(myUserid)
                .insert();
        List<Map<Integer, Object>> fans = fansService.selectByUserId(myUserid);
        return JSONResponseEntity.ok()
                .newData("fans", fans);
    }
}
