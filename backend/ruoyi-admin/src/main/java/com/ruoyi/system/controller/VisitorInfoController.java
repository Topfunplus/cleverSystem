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
import com.ruoyi.system.domain.VisitorInfo;
import com.ruoyi.system.service.IVisitorInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用于访客相关信息的Controller
 *
 * @author ruoyi
 * @date 2025-02-25
 */
@RestController
@RequestMapping("/system/visitor_info")
public class VisitorInfoController extends BaseController {
    @Autowired
    private IVisitorInfoService visitorInfoService;

    /**
     * 查询用于访客相关信息的列表
     */
    @PreAuthorize("@ss.hasPermi('system:visitor_info:list')")
    @GetMapping("/list")
    public TableDataInfo list(VisitorInfo visitorInfo) {
        startPage();
        List<VisitorInfo> list = visitorInfoService.selectVisitorInfoList(visitorInfo);
        return getDataTable(list);
    }

    /**
     * 导出用于访客相关信息的列表
     */
    @PreAuthorize("@ss.hasPermi('system:visitor_info:export')")
    @Log(title = "用于访客相关信息的", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VisitorInfo visitorInfo) {
        List<VisitorInfo> list = visitorInfoService.selectVisitorInfoList(visitorInfo);
        ExcelUtil<VisitorInfo> util = new ExcelUtil<VisitorInfo>(VisitorInfo.class);
        util.exportExcel(response, list, "用于访客相关信息的数据");
    }

    /**
     * 获取用于访客相关信息的详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:visitor_info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(visitorInfoService.selectVisitorInfoById(id));
    }

    /**
     * 新增用于访客相关信息的
     */
    @PreAuthorize("@ss.hasPermi('system:visitor_info:add')")
    @Log(title = "用于访客相关信息的", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VisitorInfo visitorInfo) {
        return toAjax(visitorInfoService.insertVisitorInfo(visitorInfo));
    }

    /**
     * 修改用于访客相关信息的
     */
    @PreAuthorize("@ss.hasPermi('system:visitor_info:edit')")
    @Log(title = "用于访客相关信息的", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VisitorInfo visitorInfo) {
        return toAjax(visitorInfoService.updateVisitorInfo(visitorInfo));
    }

    /**
     * 删除用于访客相关信息的
     */
    @PreAuthorize("@ss.hasPermi('system:visitor_info:remove')")
    @Log(title = "用于访客相关信息的", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(visitorInfoService.deleteVisitorInfoByIds(ids));
    }
}
