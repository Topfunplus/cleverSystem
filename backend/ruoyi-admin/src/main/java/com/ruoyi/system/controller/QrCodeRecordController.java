package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.QrCodeRecord;
import com.ruoyi.system.service.IQrCodeRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用于访客二维码相关记录的Controller
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
@RestController
@RequestMapping("/system/qr_code_record")
public class QrCodeRecordController extends BaseController
{
    @Autowired
    private IQrCodeRecordService qrCodeRecordService;

    /**
     * 查询用于访客二维码相关记录的列表
     */
    @PreAuthorize("@ss.hasPermi('system:qr_code_record:list')")
    @GetMapping("/list")
    public TableDataInfo list(QrCodeRecord qrCodeRecord)
    {
        startPage();
        List<QrCodeRecord> list = qrCodeRecordService.selectQrCodeRecordList(qrCodeRecord);
        return getDataTable(list);
    }

    /**
     * 导出用于访客二维码相关记录的列表
     */
    @PreAuthorize("@ss.hasPermi('system:qr_code_record:export')")
    @Log(title = "用于访客二维码相关记录的", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QrCodeRecord qrCodeRecord)
    {
        List<QrCodeRecord> list = qrCodeRecordService.selectQrCodeRecordList(qrCodeRecord);
        ExcelUtil<QrCodeRecord> util = new ExcelUtil<QrCodeRecord>(QrCodeRecord.class);
        util.exportExcel(response, list, "用于访客二维码相关记录的数据");
    }

    /**
     * 获取用于访客二维码相关记录的详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:qr_code_record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(qrCodeRecordService.selectQrCodeRecordById(id));
    }

    /**
     * 新增用于访客二维码相关记录的
     */
    @PreAuthorize("@ss.hasPermi('system:qr_code_record:add')")
    @Log(title = "用于访客二维码相关记录的", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QrCodeRecord qrCodeRecord)
    {
        return toAjax(qrCodeRecordService.insertQrCodeRecord(qrCodeRecord));
    }

    /**
     * 修改用于访客二维码相关记录的
     */
    @PreAuthorize("@ss.hasPermi('system:qr_code_record:edit')")
    @Log(title = "用于访客二维码相关记录的", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QrCodeRecord qrCodeRecord)
    {
        return toAjax(qrCodeRecordService.updateQrCodeRecord(qrCodeRecord));
    }

    /**
     * 删除用于访客二维码相关记录的
     */
    @PreAuthorize("@ss.hasPermi('system:qr_code_record:remove')")
    @Log(title = "用于访客二维码相关记录的", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(qrCodeRecordService.deleteQrCodeRecordByIds(ids));
    }
}
