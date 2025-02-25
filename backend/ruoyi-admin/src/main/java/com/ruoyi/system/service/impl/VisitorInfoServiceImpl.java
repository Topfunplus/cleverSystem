package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.VisitorInfoMapper;
import com.ruoyi.system.domain.VisitorInfo;
import com.ruoyi.system.service.IVisitorInfoService;

/**
 * 用于访客相关信息的Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
@Service
public class VisitorInfoServiceImpl implements IVisitorInfoService 
{
    @Autowired
    private VisitorInfoMapper visitorInfoMapper;

    /**
     * 查询用于访客相关信息的
     * 
     * @param id 用于访客相关信息的主键
     * @return 用于访客相关信息的
     */
    @Override
    public VisitorInfo selectVisitorInfoById(Long id)
    {
        return visitorInfoMapper.selectVisitorInfoById(id);
    }

    /**
     * 查询用于访客相关信息的列表
     * 
     * @param visitorInfo 用于访客相关信息的
     * @return 用于访客相关信息的
     */
    @Override
    public List<VisitorInfo> selectVisitorInfoList(VisitorInfo visitorInfo)
    {
        return visitorInfoMapper.selectVisitorInfoList(visitorInfo);
    }

    /**
     * 新增用于访客相关信息的
     * 
     * @param visitorInfo 用于访客相关信息的
     * @return 结果
     */
    @Override
    public int insertVisitorInfo(VisitorInfo visitorInfo)
    {
        return visitorInfoMapper.insertVisitorInfo(visitorInfo);
    }

    /**
     * 修改用于访客相关信息的
     * 
     * @param visitorInfo 用于访客相关信息的
     * @return 结果
     */
    @Override
    public int updateVisitorInfo(VisitorInfo visitorInfo)
    {
        return visitorInfoMapper.updateVisitorInfo(visitorInfo);
    }

    /**
     * 批量删除用于访客相关信息的
     * 
     * @param ids 需要删除的用于访客相关信息的主键
     * @return 结果
     */
    @Override
    public int deleteVisitorInfoByIds(Long[] ids)
    {
        return visitorInfoMapper.deleteVisitorInfoByIds(ids);
    }

    /**
     * 删除用于访客相关信息的信息
     * 
     * @param id 用于访客相关信息的主键
     * @return 结果
     */
    @Override
    public int deleteVisitorInfoById(Long id)
    {
        return visitorInfoMapper.deleteVisitorInfoById(id);
    }
}
