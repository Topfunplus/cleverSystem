package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.QrCodeRecordMapper;
import com.ruoyi.system.domain.QrCodeRecord;
import com.ruoyi.system.service.IQrCodeRecordService;

/**
 * 用于存储访客二维码相关记录的Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
@Service
public class QrCodeRecordServiceImpl implements IQrCodeRecordService 
{
    @Autowired
    private QrCodeRecordMapper qrCodeRecordMapper;

    /**
     * 查询用于存储访客二维码相关记录的
     * 
     * @param id 用于存储访客二维码相关记录的主键
     * @return 用于存储访客二维码相关记录的
     */
    @Override
    public QrCodeRecord selectQrCodeRecordById(Long id)
    {
        return qrCodeRecordMapper.selectQrCodeRecordById(id);
    }

    /**
     * 查询用于存储访客二维码相关记录的列表
     * 
     * @param qrCodeRecord 用于存储访客二维码相关记录的
     * @return 用于存储访客二维码相关记录的
     */
    @Override
    public List<QrCodeRecord> selectQrCodeRecordList(QrCodeRecord qrCodeRecord)
    {
        return qrCodeRecordMapper.selectQrCodeRecordList(qrCodeRecord);
    }

    /**
     * 新增用于存储访客二维码相关记录的
     * 
     * @param qrCodeRecord 用于存储访客二维码相关记录的
     * @return 结果
     */
    @Override
    public int insertQrCodeRecord(QrCodeRecord qrCodeRecord)
    {
        return qrCodeRecordMapper.insertQrCodeRecord(qrCodeRecord);
    }

    /**
     * 修改用于存储访客二维码相关记录的
     * 
     * @param qrCodeRecord 用于存储访客二维码相关记录的
     * @return 结果
     */
    @Override
    public int updateQrCodeRecord(QrCodeRecord qrCodeRecord)
    {
        return qrCodeRecordMapper.updateQrCodeRecord(qrCodeRecord);
    }

    /**
     * 批量删除用于存储访客二维码相关记录的
     * 
     * @param ids 需要删除的用于存储访客二维码相关记录的主键
     * @return 结果
     */
    @Override
    public int deleteQrCodeRecordByIds(Long[] ids)
    {
        return qrCodeRecordMapper.deleteQrCodeRecordByIds(ids);
    }

    /**
     * 删除用于存储访客二维码相关记录的信息
     * 
     * @param id 用于存储访客二维码相关记录的主键
     * @return 结果
     */
    @Override
    public int deleteQrCodeRecordById(Long id)
    {
        return qrCodeRecordMapper.deleteQrCodeRecordById(id);
    }
}
