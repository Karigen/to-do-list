package org.tai.todolist.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
                                  @RequestParam("task_name") String taskName) {
        new Task()
                .setUserid(userid)
                .setTaskName(taskName)
                .setDeadline(-1)
                .insert();

        List<Task> tasks = taskService.list(Wrappers.<Task>lambdaQuery()
                .eq(Task::getUserid, userid));

        return addTaskDetails(JSONResponseEntity.ok(), userid)
                .newData("tasks", tasks);
    }

    @PostMapping("/delete")
    @ApiOperation("删除任务")
    public JSONResponseEntity delete(@RequestParam("userid") Integer userid,
                                     @RequestParam("taskid") Integer taskId) {
        taskService.removeById(taskId);

        List<Task> tasks = taskService.list(Wrappers.<Task>lambdaQuery()
                .eq(Task::getUserid, userid));

        return addTaskDetails(JSONResponseEntity.ok(), userid)
                .newData("tasks", tasks);
    }

    @GetMapping("/deleteall")
    @ApiOperation("删除部分任务")
    public JSONResponseEntity deleteAll(@RequestParam("userid") Integer userid,
                                        @RequestParam("taskid") List<Integer> taskid) {

        taskService.removeBatchByIds(taskid);

        List<Task> tasks = taskService.list(Wrappers.<Task>lambdaQuery()
                .eq(Task::getUserid, userid));

        return addTaskDetails(JSONResponseEntity.ok(), userid)
                .newData("tasks", tasks);
    }

    @PostMapping("/get")
    @ApiOperation("获取所有任务")
    public JSONResponseEntity get(@RequestParam("userid") Integer userid) {
        List<Task> tasks = taskService.list(Wrappers.<Task>lambdaQuery()
                .eq(Task::getUserid, userid));

        return addTaskDetails(JSONResponseEntity.ok(), userid)
                .newData("tasks", tasks);
    }

    @PostMapping("/update")
    @ApiOperation("更新任务")
    public JSONResponseEntity update(@RequestParam("taskid") Integer taskId,
                                     @RequestParam("userid") Integer userid,
                                     @RequestParam(value = "description", required = false) String description,
                                     @RequestParam(value = "deadline", required = false) Integer deadline,
                                     @RequestParam(value = "finish", required = false) Boolean finish) {
        Task task = new Task()
                .setTaskId(taskId)
                .setDescription(description)
                .setDeadline(deadline)
                .setFinish(finish);

        taskService.update(task, Wrappers.<Task>lambdaQuery()
                .eq(Task::getTaskId, taskId));

        List<Task> tasks = taskService.list(Wrappers.<Task>lambdaQuery()
                .eq(Task::getUserid, userid));

        return JSONResponseEntity.ok()
                .newData("tasks", tasks);
    }

    public JSONResponseEntity addTaskDetails(JSONResponseEntity jsonResponseEntity, Integer userid) {
        Long unfinished = taskService.lambdaQuery()
                .eq(Task::getUserid, userid)
                .eq(Task::getFinish, false)
                .count();

        Long finished = taskService.lambdaQuery()
                .eq(Task::getUserid, userid)
                .eq(Task::getFinish, true)
                .count();

        Long all = taskService.lambdaQuery()
                .eq(Task::getUserid, userid)
                .count();

        Long isExpire = taskService.lambdaQuery()
                .eq(Task::getUserid, userid)
                .gt(Task::getDeadline, System.currentTimeMillis())
                .count();

        return jsonResponseEntity
                .newData("unfinished", unfinished)
                .newData("finished", finished)
                .newData("all", all)
                .newData("isExpire", isExpire);
    }

}
