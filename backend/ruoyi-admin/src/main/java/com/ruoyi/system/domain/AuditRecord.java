package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用于访客审核相关记录的对象 audit_record
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
public class AuditRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 审核记录ID，主键，唯一标识每条审核记录 */
    private Long id;

    /** 访客ID，外键，关联到访客信息表，标识审核记录属于哪个访客 */
    @Excel(name = "访客ID，外键，关联到访客信息表，标识审核记录属于哪个访客")
    private Long visitorId;

    /** 审核级别，表示当前审核的级别，一级或二级 */
    @Excel(name = "审核级别，表示当前审核的级别，一级或二级")
    private String auditLevel;

    /** 审核人ID，外键，关联到用户表，表示审核人的ID */
    @Excel(name = "审核人ID，外键，关联到用户表，表示审核人的ID")
    private Long auditorId;

    /** 审批状态，表示该级审核员对申请的审批结果（同意或拒绝） */
    @Excel(name = "审批状态，表示该级审核员对申请的审批结果", readConverterExp = "同=意或拒绝")
    private String approvalStatus;

    /** 审批时间，表示当前审核的时间（自动生成） */
    @Excel(name = "审批时间，表示当前审核的时间", readConverterExp = "自=动生成")
    private Date approvalTime;

    /** 审批意见，审核员对该申请的具体审批意见 */
    @Excel(name = "审批意见，审核员对该申请的具体审批意见")
    private String approvalOpinion;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVisitorId(Long visitorId) 
    {
        this.visitorId = visitorId;
    }

    public Long getVisitorId() 
    {
        return visitorId;
    }
    public void setAuditLevel(String auditLevel) 
    {
        this.auditLevel = auditLevel;
    }

    public String getAuditLevel() 
    {
        return auditLevel;
    }
    public void setAuditorId(Long auditorId) 
    {
        this.auditorId = auditorId;
    }

    public Long getAuditorId() 
    {
        return auditorId;
    }
    public void setApprovalStatus(String approvalStatus) 
    {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalStatus() 
    {
        return approvalStatus;
    }
    public void setApprovalTime(Date approvalTime) 
    {
        this.approvalTime = approvalTime;
    }

    public Date getApprovalTime() 
    {
        return approvalTime;
    }
    public void setApprovalOpinion(String approvalOpinion) 
    {
        this.approvalOpinion = approvalOpinion;
    }

    public String getApprovalOpinion() 
    {
        return approvalOpinion;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("visitorId", getVisitorId())
            .append("auditLevel", getAuditLevel())
            .append("auditorId", getAuditorId())
            .append("approvalStatus", getApprovalStatus())
            .append("approvalTime", getApprovalTime())
            .append("approvalOpinion", getApprovalOpinion())
            .toString();
    }
}
