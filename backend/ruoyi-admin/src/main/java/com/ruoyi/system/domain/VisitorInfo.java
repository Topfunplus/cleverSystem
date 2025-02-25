package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用于存储访客相关信息的对象 visitor_info
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
public class VisitorInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 访客ID，主键，唯一标识每个访客 */
    private Long id;

    /** 姓名，访客的真实姓名 */
    @Excel(name = "姓名，访客的真实姓名")
    private String name;

    /** 手机号，访客的联系电话 */
    @Excel(name = "手机号，访客的联系电话")
    private String phone;

    /** 身份证号，访客的身份证号码，用于身份识别 */
    @Excel(name = "身份证号，访客的身份证号码，用于身份识别")
    private String idCard;

    /** 访客单位，访客所在单位名称 */
    @Excel(name = "访客单位，访客所在单位名称")
    private String visitorUnit;

    /** 到访理由，访客到工厂的目的或理由 */
    @Excel(name = "到访理由，访客到工厂的目的或理由")
    private String visitReason;

    /** 身份证正面照片，存储身份证正面的图像（二进制数据） */
    @Excel(name = "身份证正面照片，存储身份证正面的图像", readConverterExp = "二=进制数据")
    private String idCardFrontImage;

    /** 身份证反面照片，存储身份证背面的图像（二进制数据） */
    @Excel(name = "身份证反面照片，存储身份证背面的图像", readConverterExp = "二=进制数据")
    private String idCardBackImage;

    /** 人脸照片，访客的脸部照片，用于闸机识别（二进制数据） */
    @Excel(name = "人脸照片，访客的脸部照片，用于闸机识别", readConverterExp = "二=进制数据")
    private String faceImage;

    /** 是否开车，0表示未开车，1表示开车，默认未开车 */
    @Excel(name = "是否开车，0表示未开车，1表示开车，默认未开车")
    private Integer isCar;

    /** 车牌号，访客的车牌号码，开车时需要填写 */
    @Excel(name = "车牌号，访客的车牌号码，开车时需要填写")
    private String carPlate;

    /** 访客时间，访客计划进入的日期（精确到天） */
    @Excel(name = "访客时间，访客计划进入的日期", readConverterExp = "精=确到天")
    private Date visitTime;

    /** 访问时长，访客预定的访问时间，单位：分钟 */
    @Excel(name = "访问时长，访客预定的访问时间，单位：分钟")
    private Long visitDuration;

    /** 申请时间，访客提交申请的时间（自动生成） */
    @Excel(name = "申请时间，访客提交申请的时间", readConverterExp = "自=动生成")
    private Date requestTime;

    /** 审核状态，表示当前申请的审批状态（待审核、已批准、已拒绝） */
    @Excel(name = "审核状态，表示当前申请的审批状态", readConverterExp = "待=审核、已批准、已拒绝")
    private String status;

    /** 一级审核时间，第一次审核的时间，表示审核员对申请的时间戳 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "一级审核时间，第一次审核的时间，表示审核员对申请的时间戳", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstApprovalTime;

    /** 一级审核意见，审核员对申请的意见或说明 */
    @Excel(name = "一级审核意见，审核员对申请的意见或说明")
    private String firstApprovalOpinion;

    /** 二级审核时间，第二次审核的时间，表示二级审核员的审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "二级审核时间，第二次审核的时间，表示二级审核员的审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date secondApprovalTime;

    /** 二级审核意见，二级审核员的审核意见或说明 */
    @Excel(name = "二级审核意见，二级审核员的审核意见或说明")
    private String secondApprovalOpinion;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setVisitorUnit(String visitorUnit) 
    {
        this.visitorUnit = visitorUnit;
    }

    public String getVisitorUnit() 
    {
        return visitorUnit;
    }
    public void setVisitReason(String visitReason) 
    {
        this.visitReason = visitReason;
    }

    public String getVisitReason() 
    {
        return visitReason;
    }
    public void setIdCardFrontImage(String idCardFrontImage) 
    {
        this.idCardFrontImage = idCardFrontImage;
    }

    public String getIdCardFrontImage() 
    {
        return idCardFrontImage;
    }
    public void setIdCardBackImage(String idCardBackImage) 
    {
        this.idCardBackImage = idCardBackImage;
    }

    public String getIdCardBackImage() 
    {
        return idCardBackImage;
    }
    public void setFaceImage(String faceImage) 
    {
        this.faceImage = faceImage;
    }

    public String getFaceImage() 
    {
        return faceImage;
    }
    public void setIsCar(Integer isCar) 
    {
        this.isCar = isCar;
    }

    public Integer getIsCar() 
    {
        return isCar;
    }
    public void setCarPlate(String carPlate) 
    {
        this.carPlate = carPlate;
    }

    public String getCarPlate() 
    {
        return carPlate;
    }
    public void setVisitTime(Date visitTime) 
    {
        this.visitTime = visitTime;
    }

    public Date getVisitTime() 
    {
        return visitTime;
    }
    public void setVisitDuration(Long visitDuration) 
    {
        this.visitDuration = visitDuration;
    }

    public Long getVisitDuration() 
    {
        return visitDuration;
    }
    public void setRequestTime(Date requestTime) 
    {
        this.requestTime = requestTime;
    }

    public Date getRequestTime() 
    {
        return requestTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setFirstApprovalTime(Date firstApprovalTime) 
    {
        this.firstApprovalTime = firstApprovalTime;
    }

    public Date getFirstApprovalTime() 
    {
        return firstApprovalTime;
    }
    public void setFirstApprovalOpinion(String firstApprovalOpinion) 
    {
        this.firstApprovalOpinion = firstApprovalOpinion;
    }

    public String getFirstApprovalOpinion() 
    {
        return firstApprovalOpinion;
    }
    public void setSecondApprovalTime(Date secondApprovalTime) 
    {
        this.secondApprovalTime = secondApprovalTime;
    }

    public Date getSecondApprovalTime() 
    {
        return secondApprovalTime;
    }
    public void setSecondApprovalOpinion(String secondApprovalOpinion) 
    {
        this.secondApprovalOpinion = secondApprovalOpinion;
    }

    public String getSecondApprovalOpinion() 
    {
        return secondApprovalOpinion;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("idCard", getIdCard())
            .append("visitorUnit", getVisitorUnit())
            .append("visitReason", getVisitReason())
            .append("idCardFrontImage", getIdCardFrontImage())
            .append("idCardBackImage", getIdCardBackImage())
            .append("faceImage", getFaceImage())
            .append("isCar", getIsCar())
            .append("carPlate", getCarPlate())
            .append("visitTime", getVisitTime())
            .append("visitDuration", getVisitDuration())
            .append("requestTime", getRequestTime())
            .append("status", getStatus())
            .append("firstApprovalTime", getFirstApprovalTime())
            .append("firstApprovalOpinion", getFirstApprovalOpinion())
            .append("secondApprovalTime", getSecondApprovalTime())
            .append("secondApprovalOpinion", getSecondApprovalOpinion())
            .toString();
    }
}
