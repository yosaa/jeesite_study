package com.jeesite.modules.wms.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.jeesite.modules.wms.config.Result;
import com.jeesite.modules.wms.util.RedisUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.wms.entity.WmsSkuInfoType;
import com.jeesite.modules.wms.service.WmsSkuInfoTypeService;

/**
 * 商品类别Controller
 * @author yosaa
 * @version 2023-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/wms/wmsSkuInfoType")
public class WmsSkuInfoTypeController extends BaseController {

	@Autowired
	private WmsSkuInfoTypeService wmsSkuInfoTypeService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	RabbitTemplate rabbitTemplate;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WmsSkuInfoType get(String typeId, boolean isNewRecord) {
		return wmsSkuInfoTypeService.get(typeId, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("wms:wmsSkuInfoType:view")
	@RequestMapping(value = {"list", ""})
	public String list(WmsSkuInfoType wmsSkuInfoType, Model model) {
		model.addAttribute("wmsSkuInfoType", wmsSkuInfoType);
		return "modules/wms/wmsSkuInfoTypeList";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("wms:wmsSkuInfoType:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WmsSkuInfoType> listData(WmsSkuInfoType wmsSkuInfoType, HttpServletRequest request, HttpServletResponse response) {
		wmsSkuInfoType.setPage(new Page<>(request, response));
		Page<WmsSkuInfoType> page = wmsSkuInfoTypeService.findPage(wmsSkuInfoType);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("wms:wmsSkuInfoType:view")
	@RequestMapping(value = "form")
	public String form(WmsSkuInfoType wmsSkuInfoType, Model model) {
		model.addAttribute("wmsSkuInfoType", wmsSkuInfoType);
		return "modules/wms/wmsSkuInfoTypeForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("wms:wmsSkuInfoType:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WmsSkuInfoType wmsSkuInfoType) {
		wmsSkuInfoTypeService.save(wmsSkuInfoType);
		return renderResult(Global.TRUE, text("保存商品类别成功！"));
	}

	/**
	 * 停用数据
	 */
	@RequiresPermissions("wms:wmsSkuInfoType:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(WmsSkuInfoType wmsSkuInfoType) {
		wmsSkuInfoType.setStatus(WmsSkuInfoType.STATUS_DISABLE);
		wmsSkuInfoTypeService.updateStatus(wmsSkuInfoType);
		return renderResult(Global.TRUE, text("停用商品类别成功"));
	}

	/**
	 * 启用数据
	 */
	@RequiresPermissions("wms:wmsSkuInfoType:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(WmsSkuInfoType wmsSkuInfoType) {
		wmsSkuInfoType.setStatus(WmsSkuInfoType.STATUS_NORMAL);
		wmsSkuInfoTypeService.updateStatus(wmsSkuInfoType);
		return renderResult(Global.TRUE, text("启用商品类别成功"));
	}

	/**
	 * 删除数据
	 */
	@RequiresPermissions("wms:wmsSkuInfoType:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WmsSkuInfoType wmsSkuInfoType) {
		wmsSkuInfoTypeService.delete(wmsSkuInfoType);
		return renderResult(Global.TRUE, text("删除商品类别成功！"));
	}

	@RequestMapping(value = "redis")
	@ResponseBody
	public Result<?> redis(String key , int type) {
		String val = "{'name': 'helloworlda','array':[{'a':'111','b':'222','c':'333'},{'a':'999'}],'address':'111','people':{'name':'happ','sex':'girl'}}";
		if(type == 1){
			Object value = redisUtil.get(key);
			return Result.ok(value);
		}else if(type == 2){
			redisUtil.set(key, val,5);
			return Result.ok("success！");
		}
		return Result.failed("error");
	}

	@RequestMapping(value = "rabbitMQ")
	@ResponseBody
	public Result<?> rabbitMQ(String key , int type) {
		Map<String,Object> map=new HashMap<>();
		map.put("yosaa",123);
		if(type == 1){
			//将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
			rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
			return Result.ok("success");
		}else if(type == 2){

			return Result.ok("success！");
		}
		return Result.failed("error");
	}

	@RequestMapping(value = "neo4j")
	@ResponseBody
	public Result<?> neo4j(String key , int type) {

		if(type == 1){



		}else if(type == 2){

			return Result.ok("success！");
		}
		return Result.failed("error");
	}
}