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
import com.ruoyi.system.domain.VisitRecord;
import com.ruoyi.system.service.IVisitRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用于存储访客访问工厂进出记录的Controller
 *
 * @author ruoyi
 * @date 2025-02-25
 */
@RestController
@RequestMapping("/system/visit_record")
public class VisitRecordController extends BaseController {
    @Autowired
    private IVisitRecordService visitRecordService;

    /**
     * 查询用于存储访客访问工厂进出记录的列表
     */
    @PreAuthorize("@ss.hasPermi('system:visit_record:list')")
    @GetMapping("/list")
    public TableDataInfo list(VisitRecord visitRecord) {
        startPage();
        List<VisitRecord> list = visitRecordService.selectVisitRecordList(visitRecord);
        return getDataTable(list);
    }

    /**
     * 导出用于存储访客访问工厂进出记录的列表
     */
    @PreAuthorize("@ss.hasPermi('system:visit_record:export')")
    @Log(title = "用于存储访客访问工厂进出记录的", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VisitRecord visitRecord) {
        List<VisitRecord> list = visitRecordService.selectVisitRecordList(visitRecord);
        ExcelUtil<VisitRecord> util = new ExcelUtil<VisitRecord>(VisitRecord.class);
        util.exportExcel(response, list, "用于存储访客访问工厂进出记录的数据");
    }

    /**
     * 获取用于存储访客访问工厂进出记录的详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:visit_record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(visitRecordService.selectVisitRecordById(id));
    }

    /**
     * 新增用于存储访客访问工厂进出记录的
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "用于存储访客访问工厂进出记录的", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VisitRecord visitRecord) {
        return toAjax(visitRecordService.insertVisitRecord(visitRecord));
    }

    /**
     * 修改用于存储访客访问工厂进出记录的
     */
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "用于存储访客访问工厂进出记录的", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VisitRecord visitRecord) {
        return toAjax(visitRecordService.updateVisitRecord(visitRecord));
    }

    /**
     * 删除用于存储访客访问工厂进出记录的
     */
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "用于存储访客访问工厂进出记录的", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(visitRecordService.deleteVisitRecordByIds(ids));
    }
}
