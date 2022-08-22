package org.tai.todolist.service;

import org.tai.todolist.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Karigen
 * @since 2022-08-09
 */
public interface BlogService extends IService<Blog> {
    List<Map<Integer,Object>> selectAllBlogsRelated(Integer userid);
}
