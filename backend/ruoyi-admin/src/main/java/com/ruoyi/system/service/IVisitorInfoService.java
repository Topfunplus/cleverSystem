package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.VisitorInfo;

/**
 * 用于访客相关信息的Service接口
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
public interface IVisitorInfoService 
{
    /**
     * 查询用于访客相关信息的
     * 
     * @param id 用于访客相关信息的主键
     * @return 用于访客相关信息的
     */
    public VisitorInfo selectVisitorInfoById(Long id);

    /**
     * 查询用于访客相关信息的列表
     * 
     * @param visitorInfo 用于访客相关信息的
     * @return 用于访客相关信息的集合
     */
    public List<VisitorInfo> selectVisitorInfoList(VisitorInfo visitorInfo);

    /**
     * 新增用于访客相关信息的
     * 
     * @param visitorInfo 用于访客相关信息的
     * @return 结果
     */
    public int insertVisitorInfo(VisitorInfo visitorInfo);

    /**
     * 修改用于访客相关信息的
     * 
     * @param visitorInfo 用于访客相关信息的
     * @return 结果
     */
    public int updateVisitorInfo(VisitorInfo visitorInfo);

    /**
     * 批量删除用于访客相关信息的
     * 
     * @param ids 需要删除的用于访客相关信息的主键集合
     * @return 结果
     */
    public int deleteVisitorInfoByIds(Long[] ids);

    /**
     * 删除用于访客相关信息的信息
     * 
     * @param id 用于访客相关信息的主键
     * @return 结果
     */
    public int deleteVisitorInfoById(Long id);
}
