package com.jeesite.modules.wms.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.entity.Page;
import com.jeesite.common.lang.DateUtils;
import com.jeesite.common.utils.excel.ExcelExport;
import com.jeesite.common.utils.excel.annotation.ExcelField.Type;
import org.springframework.web.multipart.MultipartFile;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.wms.entity.WmsSkuInfo;
import com.jeesite.modules.wms.service.WmsSkuInfoService;

/**
 * 商品详情Controller
 * @author yosaa
 * @version 2023-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/wms/wmsSkuInfo")
public class WmsSkuInfoController extends BaseController {

	@Autowired
	private WmsSkuInfoService wmsSkuInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WmsSkuInfo get(String id, boolean isNewRecord) {
		return wmsSkuInfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("wms:wmsSkuInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(WmsSkuInfo wmsSkuInfo, Model model) {
		model.addAttribute("wmsSkuInfo", wmsSkuInfo);
		return "modules/wms/wmsSkuInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("wms:wmsSkuInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WmsSkuInfo> listData(WmsSkuInfo wmsSkuInfo, HttpServletRequest request, HttpServletResponse response) {
		wmsSkuInfo.setPage(new Page<>(request, response));
		Page<WmsSkuInfo> page = wmsSkuInfoService.findPage(wmsSkuInfo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("wms:wmsSkuInfo:view")
	@RequestMapping(value = "form")
	public String form(WmsSkuInfo wmsSkuInfo, Model model) {
		model.addAttribute("wmsSkuInfo", wmsSkuInfo);
		return "modules/wms/wmsSkuInfoForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("wms:wmsSkuInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WmsSkuInfo wmsSkuInfo) {
		wmsSkuInfoService.save(wmsSkuInfo);
		return renderResult(Global.TRUE, text("保存商品详情成功！"));
	}

	/**
	 * 导出数据
	 */
	@RequiresPermissions("wms:wmsSkuInfo:view")
	@RequestMapping(value = "exportData")
	public void exportData(WmsSkuInfo wmsSkuInfo, HttpServletResponse response) {
		List<WmsSkuInfo> list = wmsSkuInfoService.findList(wmsSkuInfo);
		String fileName = "商品详情" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
		try(ExcelExport ee = new ExcelExport("商品详情", WmsSkuInfo.class)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 下载模板
	 */
	@RequiresPermissions("wms:wmsSkuInfo:view")
	@RequestMapping(value = "importTemplate")
	public void importTemplate(HttpServletResponse response) {
		WmsSkuInfo wmsSkuInfo = new WmsSkuInfo();
		List<WmsSkuInfo> list = ListUtils.newArrayList(wmsSkuInfo);
		String fileName = "商品详情模板.xlsx";
		try(ExcelExport ee = new ExcelExport("商品详情", WmsSkuInfo.class, Type.IMPORT)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 导入数据
	 */
	@ResponseBody
	@RequiresPermissions("wms:wmsSkuInfo:edit")
	@PostMapping(value = "importData")
	public String importData(MultipartFile file) {
		try {
			String message = wmsSkuInfoService.importData(file);
			return renderResult(Global.TRUE, "posfull:"+message);
		} catch (Exception ex) {
			return renderResult(Global.FALSE, "posfull:"+ex.getMessage());
		}
	}
	
	/**
	 * 停用数据
	 */
	@RequiresPermissions("wms:wmsSkuInfo:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(WmsSkuInfo wmsSkuInfo) {
		wmsSkuInfo.setStatus(WmsSkuInfo.STATUS_DISABLE);
		wmsSkuInfoService.updateStatus(wmsSkuInfo);
		return renderResult(Global.TRUE, text("停用商品详情成功"));
	}
	
	/**
	 * 启用数据
	 */
	@RequiresPermissions("wms:wmsSkuInfo:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(WmsSkuInfo wmsSkuInfo) {
		wmsSkuInfo.setStatus(WmsSkuInfo.STATUS_NORMAL);
		wmsSkuInfoService.updateStatus(wmsSkuInfo);
		return renderResult(Global.TRUE, text("启用商品详情成功"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("wms:wmsSkuInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WmsSkuInfo wmsSkuInfo) {
		wmsSkuInfoService.delete(wmsSkuInfo);
		return renderResult(Global.TRUE, text("删除商品详情成功！"));
	}
	
}