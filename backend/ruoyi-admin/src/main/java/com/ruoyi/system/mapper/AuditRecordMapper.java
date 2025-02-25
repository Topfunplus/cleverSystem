package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AuditRecord;

/**
 * 用于访客审核相关记录的Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
public interface AuditRecordMapper 
{
    /**
     * 查询用于访客审核相关记录的
     * 
     * @param id 用于访客审核相关记录的主键
     * @return 用于访客审核相关记录的
     */
    public AuditRecord selectAuditRecordById(Long id);

    /**
     * 查询用于访客审核相关记录的列表
     * 
     * @param auditRecord 用于访客审核相关记录的
     * @return 用于访客审核相关记录的集合
     */
    public List<AuditRecord> selectAuditRecordList(AuditRecord auditRecord);

    /**
     * 新增用于访客审核相关记录的
     * 
     * @param auditRecord 用于访客审核相关记录的
     * @return 结果
     */
    public int insertAuditRecord(AuditRecord auditRecord);

    /**
     * 修改用于访客审核相关记录的
     * 
     * @param auditRecord 用于访客审核相关记录的
     * @return 结果
     */
    public int updateAuditRecord(AuditRecord auditRecord);

    /**
     * 删除用于访客审核相关记录的
     * 
     * @param id 用于访客审核相关记录的主键
     * @return 结果
     */
    public int deleteAuditRecordById(Long id);

    /**
     * 批量删除用于访客审核相关记录的
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAuditRecordByIds(Long[] ids);
}
