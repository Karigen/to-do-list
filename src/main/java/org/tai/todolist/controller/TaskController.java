package org.tai.todolist.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tai.todolist.entity.JSONResponseEntity;
import org.tai.todolist.entity.Task;
import org.tai.todolist.service.TaskService;

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
@RequestMapping("/task")
@Api("任务管理")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    @ApiOperation("新建任务")
    public JSONResponseEntity add(@RequestParam("userid") Integer userid,
                                  @RequestParam("task-name") String taskName) {
        new Task()
                .setUserid(userid)
                .setTaskName(taskName)
                .setDeadline(-1)
                .insert();

        List<Task> tasks = taskService.list(Wrappers.<Task>query()
                .eq("userid", userid));

        return JSONResponseEntity.ok()
                .newData("tasks", tasks);
    }

    @PostMapping("/delete")
    @ApiOperation("删除任务")
    public JSONResponseEntity delete(@RequestParam("userid") Integer userid,
                                     @RequestParam("taskid") Integer taskId) {
        taskService.removeById(taskId);

        List<Task> tasks = taskService.list(Wrappers.<Task>query()
                .eq("userid", userid));

        return JSONResponseEntity.ok()
                .newData("tasks", tasks);
    }

    @PostMapping("/deleteall")
    @ApiOperation("删除部分任务")
    public JSONResponseEntity deleteAll(@RequestParam("userid") Integer userid,
                                        @RequestParam("taskid") List<Integer> taskId) {
        taskService.removeBatchByIds(taskId);

        List<Task> tasks = taskService.list(Wrappers.<Task>query()
                .eq("userid", userid));

        return JSONResponseEntity.ok()
                .newData("tasks", tasks);
    }

    @PostMapping("/get")
    @ApiOperation("获取所有任务")
    public JSONResponseEntity get(@RequestParam("userid") Integer userid) {
        List<Task> tasks = taskService.list(Wrappers.<Task>query()
                .eq("userid", userid));

        return JSONResponseEntity.ok()
                .newData("tasks", tasks);
    }

    @PostMapping("/update")
    @ApiOperation("更新任务")
    public JSONResponseEntity update(@RequestParam("userid") Integer userid,
                                     @RequestParam("description") String description,
                                     @RequestParam("deadline") Integer deadline,
                                     @RequestParam("finish") Boolean finish) {
        Task task = new Task()
                .setUserid(userid)
                .setDescription(description)
                .setDeadline(deadline)
                .setFinish(finish);

        taskService.update(Wrappers.update(task));

        List<Task> tasks = taskService.list(Wrappers.<Task>query()
                .eq("userid", userid));

        return JSONResponseEntity.ok()
                .newData("tasks", tasks);
    }

}
