package org.tai.todolist.service.impl;

import org.tai.todolist.entity.Task;
import org.tai.todolist.dao.TaskMapper;
import org.tai.todolist.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Karigen
 * @since 2022-08-09
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

}
