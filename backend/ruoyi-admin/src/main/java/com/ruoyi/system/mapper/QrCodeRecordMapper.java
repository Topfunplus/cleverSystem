package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.QrCodeRecord;

/**
 * 用于存储访客二维码相关记录的Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
public interface QrCodeRecordMapper 
{
    /**
     * 查询用于存储访客二维码相关记录的
     * 
     * @param id 用于存储访客二维码相关记录的主键
     * @return 用于存储访客二维码相关记录的
     */
    public QrCodeRecord selectQrCodeRecordById(Long id);

    /**
     * 查询用于存储访客二维码相关记录的列表
     * 
     * @param qrCodeRecord 用于存储访客二维码相关记录的
     * @return 用于存储访客二维码相关记录的集合
     */
    public List<QrCodeRecord> selectQrCodeRecordList(QrCodeRecord qrCodeRecord);

    /**
     * 新增用于存储访客二维码相关记录的
     * 
     * @param qrCodeRecord 用于存储访客二维码相关记录的
     * @return 结果
     */
    public int insertQrCodeRecord(QrCodeRecord qrCodeRecord);

    /**
     * 修改用于存储访客二维码相关记录的
     * 
     * @param qrCodeRecord 用于存储访客二维码相关记录的
     * @return 结果
     */
    public int updateQrCodeRecord(QrCodeRecord qrCodeRecord);

    /**
     * 删除用于存储访客二维码相关记录的
     * 
     * @param id 用于存储访客二维码相关记录的主键
     * @return 结果
     */
    public int deleteQrCodeRecordById(Long id);

    /**
     * 批量删除用于存储访客二维码相关记录的
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQrCodeRecordByIds(Long[] ids);
}
