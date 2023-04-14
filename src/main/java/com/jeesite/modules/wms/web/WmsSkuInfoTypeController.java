package com.jeesite.modules.wms.web;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.wms.config.neo4j.*;
import com.jeesite.modules.wms.config.Result;
import com.jeesite.modules.wms.entity.neo4j.*;
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
	@Autowired
	TypeOf1Repository typeOf1Repository;
	@Autowired
	PonitOf2Repository ponitOf2Repository;
	@Autowired
	MainTagOf3Repository mainTagOf3Repository;
	@Autowired
	TagOf4Repository tagOf4Repository;
	@Autowired
	CompanyOf5Repository companyOf5Repository;

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

			// 删除所有节点和关系（删除节点会响应删除关联关系），避免后续创建节点重复影响
			typeOf1Repository.deleteAll();
			ponitOf2Repository.deleteAll();
			mainTagOf3Repository.deleteAll();
			tagOf4Repository.deleteAll();
			companyOf5Repository.deleteAll();


			// 创建节点
			TypeOf1Entity type1 = new TypeOf1Entity("数字中国建设基础","xxxxxxx");

			PointOf2Entity point1 = new PointOf2Entity("打通数字基础设施大动脉","xxxx");
			PointOf2Entity point2 = new PointOf2Entity("畅通数据资源大循环","xxxx");

			MainTagOf3Entity mainTag1 = new MainTagOf3Entity("5G网络与干兆光纤","xxx");
			MainTagOf3Entity mainTag2 = new MainTagOf3Entity("物联网模组与智能控制器","xxx");
			MainTagOf3Entity mainTag3 = new MainTagOf3Entity("北斗","xxx");
			MainTagOf3Entity mainTag4 = new MainTagOf3Entity("算力基础设施布局","xxx");
			MainTagOf3Entity mainTag5 = new MainTagOf3Entity("数据处理","xxx");
			MainTagOf3Entity mainTag6 = new MainTagOf3Entity("数据流通","xxx");

			TagOf4Entity tag1 = new TagOf4Entity("运营商","xx");
			TagOf4Entity tag2 = new TagOf4Entity("光通信","xx");
			TagOf4Entity tag3 = new TagOf4Entity("光器件","xx");
			TagOf4Entity tag4 = new TagOf4Entity("光芯片","xx");
			TagOf4Entity tag5 = new TagOf4Entity("光通信","xx");
			TagOf4Entity tag6 = new TagOf4Entity("光纤光缆","xx");
			TagOf4Entity tag7 = new TagOf4Entity("系统设备","xx");
			TagOf4Entity tag8 = new TagOf4Entity("网络设备","xx");
			TagOf4Entity tag9 = new TagOf4Entity("代工商","xx");
			TagOf4Entity tag10 = new TagOf4Entity("配套设备","xx");

			CompanyOf5Entity company1 = new CompanyOf5Entity("中国移动","xx");
			CompanyOf5Entity company2 = new CompanyOf5Entity("中国电信","xx");
			CompanyOf5Entity company3 = new CompanyOf5Entity("中国联通","xx");


			CompanyOf5Entity company4 = new CompanyOf5Entity("移远通信","xx");
			CompanyOf5Entity company5 = new CompanyOf5Entity("广和通","xx");
			CompanyOf5Entity company6 = new CompanyOf5Entity("美格智能","xx");
			CompanyOf5Entity company7 = new CompanyOf5Entity("和而泰","xx");
			CompanyOf5Entity company8 = new CompanyOf5Entity("拓邦股份","xx");

			CompanyOf5Entity company9 = new CompanyOf5Entity("中际旭创","xx");
			CompanyOf5Entity company10 = new CompanyOf5Entity("新易盛","xx");
			CompanyOf5Entity company11 = new CompanyOf5Entity("光迅科技","xx");
			CompanyOf5Entity company12 = new CompanyOf5Entity("剑桥科技","xx");
			CompanyOf5Entity company13 = new CompanyOf5Entity("德科立","xx");
			CompanyOf5Entity company14 = new CompanyOf5Entity("联特科技","xx");

			CompanyOf5Entity company15 = new CompanyOf5Entity("天孚通信","xx");
			CompanyOf5Entity company16 = new CompanyOf5Entity("太辰光","xx");
			CompanyOf5Entity company17 = new CompanyOf5Entity("腾景科技","xx");

			CompanyOf5Entity company18 = new CompanyOf5Entity("源杰科技","xx");
			CompanyOf5Entity company19 = new CompanyOf5Entity("仕佳光子","xx");
			CompanyOf5Entity company20 = new CompanyOf5Entity("光库科技","xx");

			CompanyOf5Entity company21 = new CompanyOf5Entity("长飞光纤","xx");
			CompanyOf5Entity company22 = new CompanyOf5Entity("中天科技","xx");
			CompanyOf5Entity company23 = new CompanyOf5Entity("亨通光电","xx");

			CompanyOf5Entity company24 = new CompanyOf5Entity("中兴通讯","xx");
			CompanyOf5Entity company25 = new CompanyOf5Entity("烽火通信","xx");

			CompanyOf5Entity company26 = new CompanyOf5Entity("紫光股份","xx");
			CompanyOf5Entity company27 = new CompanyOf5Entity("锐捷网络","xx");

			CompanyOf5Entity company28 = new CompanyOf5Entity("菲菱科思","xx");
			CompanyOf5Entity company29 = new CompanyOf5Entity("共进股份","xx");

			CompanyOf5Entity company30 = new CompanyOf5Entity("英维克","xx");
			CompanyOf5Entity company31 = new CompanyOf5Entity("科华数据","xx");


			Collections.addAll(type1.getInclude(),point1,point2);

			Collections.addAll(point1.getInclude(),mainTag1,mainTag2,mainTag3,mainTag4,mainTag5,mainTag6);

			Collections.addAll(mainTag1.getInclude(),tag1,tag2,tag3,tag4,tag5,tag6,tag7,tag8,tag9,tag10);
			Collections.addAll(mainTag2.getIncludeSkip(),company4,company5,company6,company7,company8);

			Collections.addAll(tag1.getInclude(),company1,company2,company3);

			Collections.addAll(tag2.getInclude(),company9,company10,company11,company12,company13,company14);
			Collections.addAll(tag3.getInclude(),company15,company16,company17);
			Collections.addAll(tag4.getInclude(),company18,company19,company20);
			Collections.addAll(tag5.getInclude(),company21,company22,company23);
			Collections.addAll(tag6.getInclude(),company24,company25);
			Collections.addAll(tag7.getInclude(),company26,company27);
			Collections.addAll(tag8.getInclude(),company28,company29);
			Collections.addAll(tag9.getInclude(),company30,company31);
			typeOf1Repository.save(type1);

			return Result.ok("success！");
		}else if(type == 2){


			// 查询
			CompanyOf5Entity companyInfo = companyOf5Repository.findCompanyByTag("中国移动");
			System.out.println("\n查询名字为“中国移动”的CompanyOf5Entity：\n"+companyInfo);


			// 更新(更新主要是三步：1.获取实体id；2.修改实体属性；3.更新实体）
			// 注意：repository的save方法【对应的实体若id一致】则为修改，否则为新建。
			Long companyId = companyInfo.getId();
			companyInfo.setInfo("公司公司");
			companyOf5Repository.save(companyInfo);
			companyInfo = companyOf5Repository.findCompanyByTag("中国移动");
			System.out.println(companyId == companyInfo.getId()?"\n更新信息成功！：\n"+companyInfo:"更新信息失败！");


			// 新增
			CompanyOf5Entity companyNew = new CompanyOf5Entity("测试公司名2","xxxx");
			TagOf4Entity testTag = tagOf4Repository.findTagByTag("运营商");
			testTag.getInclude().add(companyNew);
			tagOf4Repository.save(testTag);

			return Result.ok("success！");
		}else if(type == 3){
			MainTagOf3Entity mainTagOf3Info = mainTagOf3Repository.findMainTagByTag("5G网络与干兆光纤");
			System.out.println( "xxxxx" + mainTagOf3Info);
			return Result.ok(mainTagOf3Info);
		}
		return Result.failed("error");
	}
}