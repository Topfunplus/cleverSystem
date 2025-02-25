package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.VisitRecordMapper;
import com.ruoyi.system.domain.VisitRecord;
import com.ruoyi.system.service.IVisitRecordService;

/**
 * 用于存储访客访问工厂进出记录的Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
@Service
public class VisitRecordServiceImpl implements IVisitRecordService 
{
    @Autowired
    private VisitRecordMapper visitRecordMapper;

    /**
     * 查询用于存储访客访问工厂进出记录的
     * 
     * @param id 用于存储访客访问工厂进出记录的主键
     * @return 用于存储访客访问工厂进出记录的
     */
    @Override
    public VisitRecord selectVisitRecordById(Long id)
    {
        return visitRecordMapper.selectVisitRecordById(id);
    }

    /**
     * 查询用于存储访客访问工厂进出记录的列表
     * 
     * @param visitRecord 用于存储访客访问工厂进出记录的
     * @return 用于存储访客访问工厂进出记录的
     */
    @Override
    public List<VisitRecord> selectVisitRecordList(VisitRecord visitRecord)
    {
        return visitRecordMapper.selectVisitRecordList(visitRecord);
    }

    /**
     * 新增用于存储访客访问工厂进出记录的
     * 
     * @param visitRecord 用于存储访客访问工厂进出记录的
     * @return 结果
     */
    @Override
    public int insertVisitRecord(VisitRecord visitRecord)
    {
        return visitRecordMapper.insertVisitRecord(visitRecord);
    }

    /**
     * 修改用于存储访客访问工厂进出记录的
     * 
     * @param visitRecord 用于存储访客访问工厂进出记录的
     * @return 结果
     */
    @Override
    public int updateVisitRecord(VisitRecord visitRecord)
    {
        return visitRecordMapper.updateVisitRecord(visitRecord);
    }

    /**
     * 批量删除用于存储访客访问工厂进出记录的
     * 
     * @param ids 需要删除的用于存储访客访问工厂进出记录的主键
     * @return 结果
     */
    @Override
    public int deleteVisitRecordByIds(Long[] ids)
    {
        return visitRecordMapper.deleteVisitRecordByIds(ids);
    }

    /**
     * 删除用于存储访客访问工厂进出记录的信息
     * 
     * @param id 用于存储访客访问工厂进出记录的主键
     * @return 结果
     */
    @Override
    public int deleteVisitRecordById(Long id)
    {
        return visitRecordMapper.deleteVisitRecordById(id);
    }
}
