package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AuditRecordMapper;
import com.ruoyi.system.domain.AuditRecord;
import com.ruoyi.system.service.IAuditRecordService;

/**
 * 用于访客审核相关记录的Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
@Service
public class AuditRecordServiceImpl implements IAuditRecordService 
{
    @Autowired
    private AuditRecordMapper auditRecordMapper;

    /**
     * 查询用于访客审核相关记录的
     * 
     * @param id 用于访客审核相关记录的主键
     * @return 用于访客审核相关记录的
     */
    @Override
    public AuditRecord selectAuditRecordById(Long id)
    {
        return auditRecordMapper.selectAuditRecordById(id);
    }

    /**
     * 查询用于访客审核相关记录的列表
     * 
     * @param auditRecord 用于访客审核相关记录的
     * @return 用于访客审核相关记录的
     */
    @Override
    public List<AuditRecord> selectAuditRecordList(AuditRecord auditRecord)
    {
        return auditRecordMapper.selectAuditRecordList(auditRecord);
    }

    /**
     * 新增用于访客审核相关记录的
     * 
     * @param auditRecord 用于访客审核相关记录的
     * @return 结果
     */
    @Override
    public int insertAuditRecord(AuditRecord auditRecord)
    {
        return auditRecordMapper.insertAuditRecord(auditRecord);
    }

    /**
     * 修改用于访客审核相关记录的
     * 
     * @param auditRecord 用于访客审核相关记录的
     * @return 结果
     */
    @Override
    public int updateAuditRecord(AuditRecord auditRecord)
    {
        return auditRecordMapper.updateAuditRecord(auditRecord);
    }

    /**
     * 批量删除用于访客审核相关记录的
     * 
     * @param ids 需要删除的用于访客审核相关记录的主键
     * @return 结果
     */
    @Override
    public int deleteAuditRecordByIds(Long[] ids)
    {
        return auditRecordMapper.deleteAuditRecordByIds(ids);
    }

    /**
     * 删除用于访客审核相关记录的信息
     * 
     * @param id 用于访客审核相关记录的主键
     * @return 结果
     */
    @Override
    public int deleteAuditRecordById(Long id)
    {
        return auditRecordMapper.deleteAuditRecordById(id);
    }
}
