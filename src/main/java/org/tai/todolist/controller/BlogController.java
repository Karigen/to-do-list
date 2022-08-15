package org.tai.todolist.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tai.todolist.entity.Blog;
import org.tai.todolist.entity.Fans;
import org.tai.todolist.entity.JSONResponseEntity;
import org.tai.todolist.entity.User;
import org.tai.todolist.exception.BusinessException;
import org.tai.todolist.exception.ErrorCode;
import org.tai.todolist.service.BlogService;
import org.tai.todolist.service.FansService;
import org.tai.todolist.service.UserService;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Karigen
 * @since 2022-08-09
 */
@RestController
@RequestMapping("/blog")
@CrossOrigin
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private FansService fansService;

    @Autowired
    private UserService userService;

    @PostMapping("/get")
    @ApiOperation("获取用户粉丝数,博客数,follow数和所有博客")
    public JSONResponseEntity get(@RequestParam("userid") Integer userid) {
        Long fans = fansService.lambdaQuery()
                .eq(Fans::getUserid, userid)
                .count();

        Long count = blogService.lambdaQuery()
                .eq(Blog::getUserid, userid)
                .count();

        Long follow = fansService.lambdaQuery()
                .eq(Fans::getFanId, userid)
                .count();

        List<Blog> blogs = blogService.list(Wrappers.<Blog>lambdaQuery()
                .eq(Blog::getUserid, userid));

        return JSONResponseEntity.ok()
                .newData("fans", fans)
                .newData("count", count)
                .newData("follow", follow)
                .newData("blogs", blogs);
    }

    private long getCurrentTimeHours() {
        return System.currentTimeMillis() / (60 * 60 * 1_000);
    }

    @PostMapping("/add")
    @ApiOperation("新建博客")
    public JSONResponseEntity add(@RequestParam("userid") Integer userid,
                                  @RequestParam("context") String context) {
        new Blog()
                .setUserid(userid)
                .setContext(context)
                .setPostTime((int) getCurrentTimeHours())
                .insert();

        return JSONResponseEntity.ok();
    }

    @PostMapping("/users")
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

        return JSONResponseEntity.ok()
                .newData("userid", follow.getUserid());
    }

}
