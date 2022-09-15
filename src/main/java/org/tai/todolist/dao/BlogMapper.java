package org.tai.todolist.dao;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.tai.todolist.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Karigen
 * @since 2022-08-09
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

    @MapKey("blogId")
    List<Map<Integer, Object>> selectAllBlogsRelated(Integer userid);


    List<Map<Integer, Object>> selectAllBlogs();

    void deleteBlog(Integer blogId);
}
