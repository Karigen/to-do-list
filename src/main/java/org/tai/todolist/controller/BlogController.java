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
import java.util.Map;

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

        List<Map<Integer, Object>> blogs = blogService.selectAllBlogsRelated(userid);

        return JSONResponseEntity.ok()
                .newData("fans", fans)
                .newData("count", count)
                .newData("follow", follow)
                .newData("blogs", blogs);
    }

    @PostMapping("/add")
    @ApiOperation("新建博客")
    public JSONResponseEntity add(@RequestParam("userid") Integer userid,
                                  @RequestParam("context") String context) {
        new Blog()
                .setUserid(userid)
                .setContext(context)
                .setPostTime((int) (System.currentTimeMillis() / 1_000))
                .insert();

        return JSONResponseEntity.ok();
    }



    @PostMapping("/delete")
    @ApiOperation("删除本条blog")
    public JSONResponseEntity delete(@RequestParam("blogId") Integer blogid) {
        blogService.lambdaUpdate()
                .eq(Blog::getBlogId, blogid)
                .remove();
        return JSONResponseEntity.ok();
    }

    @PostMapping("/update")
    @ApiOperation("更新博客")
    public JSONResponseEntity update(@RequestParam("blogId") Integer blogid,
                                     @RequestParam("context") String context) {
        blogService.lambdaUpdate()
                .eq(Blog::getBlogId, blogid)
                .set(Blog::getContext, context)
                .update();
        return JSONResponseEntity.ok();
    }


}
