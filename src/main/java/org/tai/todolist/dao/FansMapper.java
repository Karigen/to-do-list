package org.tai.todolist.dao;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.tai.todolist.entity.Fans;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Karigen
 * @since 2022-08-09
 */
@Mapper
public interface FansMapper extends BaseMapper<Fans> {
    @MapKey("userId")
    List<Map<Integer,Object>> selectByUserId(Integer userid);

    @MapKey("fanId")
    List<Map<Integer, Object>> selectByFanId(Integer userid);
}
