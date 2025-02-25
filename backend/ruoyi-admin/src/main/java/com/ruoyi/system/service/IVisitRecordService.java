package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.VisitRecord;

/**
 * 用于访客访问工厂进出记录的Service接口
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
public interface IVisitRecordService 
{
    /**
     * 查询用于访客访问工厂进出记录的
     * 
     * @param id 用于访客访问工厂进出记录的主键
     * @return 用于访客访问工厂进出记录的
     */
    public VisitRecord selectVisitRecordById(Long id);

    /**
     * 查询用于访客访问工厂进出记录的列表
     * 
     * @param visitRecord 用于访客访问工厂进出记录的
     * @return 用于访客访问工厂进出记录的集合
     */
    public List<VisitRecord> selectVisitRecordList(VisitRecord visitRecord);

    /**
     * 新增用于访客访问工厂进出记录的
     * 
     * @param visitRecord 用于访客访问工厂进出记录的
     * @return 结果
     */
    public int insertVisitRecord(VisitRecord visitRecord);

    /**
     * 修改用于访客访问工厂进出记录的
     * 
     * @param visitRecord 用于访客访问工厂进出记录的
     * @return 结果
     */
    public int updateVisitRecord(VisitRecord visitRecord);

    /**
     * 批量删除用于访客访问工厂进出记录的
     * 
     * @param ids 需要删除的用于访客访问工厂进出记录的主键集合
     * @return 结果
     */
    public int deleteVisitRecordByIds(Long[] ids);

    /**
     * 删除用于访客访问工厂进出记录的信息
     * 
     * @param id 用于访客访问工厂进出记录的主键
     * @return 结果
     */
    public int deleteVisitRecordById(Long id);
}
