package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用于访客二维码相关记录的对象 qr_code_record
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
public class QrCodeRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 二维码ID，主键，唯一标识每条二维码记录 */
    private Long id;

    /** 访客ID，外键，关联到访客信息表，表示该二维码对应的访客 */
    @Excel(name = "访客ID，外键，关联到访客信息表，表示该二维码对应的访客")
    private Long visitorId;

    /** 二维码内容，存储二维码的字符串，包含访客的基本信息（如姓名、身份证号等） */
    @Excel(name = "二维码内容，存储二维码的字符串，包含访客的基本信息", readConverterExp = "如=姓名、身份证号等")
    private String qrCode;

    /** 创建时间，二维码生成的时间（自动生成） */
    @Excel(name = "创建时间，二维码生成的时间", readConverterExp = "自=动生成")
    private Date creationTime;

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
    public void setQrCode(String qrCode) 
    {
        this.qrCode = qrCode;
    }

    public String getQrCode() 
    {
        return qrCode;
    }
    public void setCreationTime(Date creationTime) 
    {
        this.creationTime = creationTime;
    }

    public Date getCreationTime() 
    {
        return creationTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("visitorId", getVisitorId())
            .append("qrCode", getQrCode())
            .append("creationTime", getCreationTime())
            .toString();
    }
}
