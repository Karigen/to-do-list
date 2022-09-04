package org.tai.todolist.service.impl;

import org.apache.ibatis.annotations.MapKey;
import org.tai.todolist.entity.Blog;
import org.tai.todolist.dao.BlogMapper;
import org.tai.todolist.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Karigen
 * @since 2022-08-09
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    public List<Map<Integer, Object>> selectAllBlogsRelated(Integer userid) {
        return baseMapper.selectAllBlogsRelated(userid);
    }
}
