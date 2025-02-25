package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用于存储访客访问工厂进出记录的对象 visit_record
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
public class VisitRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 访问记录ID，主键，唯一标识每条进出记录 */
    private Long id;

    /** 访客ID，外键，关联到访客信息表，标识该访问记录属于哪个访客 */
    @Excel(name = "访客ID，外键，关联到访客信息表，标识该访问记录属于哪个访客")
    private Long visitorId;

    /** 进入时间，访客进入工厂的具体时间（时间戳） */
    @Excel(name = "进入时间，访客进入工厂的具体时间", readConverterExp = "时=间戳")
    private Date entryTime;

    /** 离开时间，访客离开工厂的具体时间（时间戳） */
    @Excel(name = "离开时间，访客离开工厂的具体时间", readConverterExp = "时=间戳")
    private Date exitTime;

    /** 是否是重新进入，0表示首次进入，1表示重新进入，默认首次进入 */
    @Excel(name = "是否是重新进入，0表示首次进入，1表示重新进入，默认首次进入")
    private Integer isReentry;

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
    public void setEntryTime(Date entryTime) 
    {
        this.entryTime = entryTime;
    }

    public Date getEntryTime() 
    {
        return entryTime;
    }
    public void setExitTime(Date exitTime) 
    {
        this.exitTime = exitTime;
    }

    public Date getExitTime() 
    {
        return exitTime;
    }
    public void setIsReentry(Integer isReentry) 
    {
        this.isReentry = isReentry;
    }

    public Integer getIsReentry() 
    {
        return isReentry;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("visitorId", getVisitorId())
            .append("entryTime", getEntryTime())
            .append("exitTime", getExitTime())
            .append("isReentry", getIsReentry())
            .toString();
    }
}
