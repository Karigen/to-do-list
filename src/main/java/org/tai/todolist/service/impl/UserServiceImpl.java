package org.tai.todolist.service.impl;

import org.tai.todolist.entity.User;
import org.tai.todolist.aop.dao.UserMapper;
import org.tai.todolist.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
