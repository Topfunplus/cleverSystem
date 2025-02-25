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
import com.ruoyi.system.domain.AuditRecord;
import com.ruoyi.system.service.IAuditRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用于访客审核相关记录的Controller
 *
 * @author ruoyi
 * @date 2025-02-25
 */
@RestController
@RequestMapping("/system/audit_record")
public class AuditRecordController extends BaseController {
    @Autowired
    private IAuditRecordService auditRecordService;

    /**
     * 查询用于访客审核相关记录的列表
     */
    @PreAuthorize("@ss.hasPermi('system:audit_record:list')")
    @GetMapping("/list")
    public TableDataInfo list(AuditRecord auditRecord) {
        startPage();
        List<AuditRecord> list = auditRecordService.selectAuditRecordList(auditRecord);
        return getDataTable(list);
    }

    /**
     * 导出用于访客审核相关记录的列表
     */
    @PreAuthorize("@ss.hasPermi('system:audit_record:export')")
    @Log(title = "用于访客审核相关记录的", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AuditRecord auditRecord) {
        List<AuditRecord> list = auditRecordService.selectAuditRecordList(auditRecord);
        ExcelUtil<AuditRecord> util = new ExcelUtil<AuditRecord>(AuditRecord.class);
        util.exportExcel(response, list, "用于访客审核相关记录的数据");
    }

    /**
     * 获取用于访客审核相关记录的详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:audit_record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(auditRecordService.selectAuditRecordById(id));
    }

    /**
     * 新增用于访客审核相关记录的
     */
    @PreAuthorize("@ss.hasPermi('system:audit_record:add')")
    @Log(title = "用于访客审核相关记录的", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AuditRecord auditRecord) {
        return toAjax(auditRecordService.insertAuditRecord(auditRecord));
    }

    /**
     * 修改用于访客审核相关记录的
     */
    @PreAuthorize("@ss.hasPermi('system:audit_record:edit')")
    @Log(title = "用于访客审核相关记录的", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AuditRecord auditRecord) {
        return toAjax(auditRecordService.updateAuditRecord(auditRecord));
    }

    /**
     * 删除用于访客审核相关记录的
     */
    @PreAuthorize("@ss.hasPermi('system:audit_record:remove')")
    @Log(title = "用于访客审核相关记录的", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(auditRecordService.deleteAuditRecordByIds(ids));
    }
}
