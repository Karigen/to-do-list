package org.tai.todolist.service;

import org.tai.todolist.entity.Fans;
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
public interface FansService extends IService<Fans> {
    List<Map<Integer,Object>> selectByUserId(Integer userid);

    List<Map<Integer, Object>> selectByFanId(Integer userid);

    List<Map<Integer, Object>> selectCommonFollows(Integer fan1Id, Integer fan2Id);

    List<Map<Integer,Object>> selectMutualFollows(Integer fanId);

    void deleteFan(Integer fanId, Integer userId);
}
