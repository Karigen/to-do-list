package org.tai.todolist.service.impl;

import org.tai.todolist.entity.Fans;
import org.tai.todolist.dao.FansMapper;
import org.tai.todolist.service.FansService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class FansServiceImpl extends ServiceImpl<FansMapper, Fans> implements FansService {
    public List<Map<Integer, Object>> selectByUserId(Integer userid) {
        return baseMapper.selectByUserId(userid);
    }

    public List<Map<Integer, Object>> selectByFanId(Integer userid) {
        return baseMapper.selectByFanId(userid);
    }

    @Override
    public List<Map<Integer, Object>> selectCommonFollows(Integer fan1Id, Integer fan2Id) {
        return baseMapper.selectCommonFollows(fan1Id, fan2Id);
    }

    @Override
    public List<Map<Integer, Object>> selectMutualFollows(Integer fanId) {
        return baseMapper.selectMutualFollows(fanId);
    }

    @Override
    public void deleteFan(Integer fanId, Integer userId) {
        baseMapper.deleteFan(fanId, userId);
    }
}
