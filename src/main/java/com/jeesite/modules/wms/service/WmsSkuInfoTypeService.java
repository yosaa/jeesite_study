package com.jeesite.modules.wms.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.wms.entity.WmsSkuInfoType;
import com.jeesite.modules.wms.dao.WmsSkuInfoTypeDao;

/**
 * 商品类别Service
 * @author yosaa
 * @version 2023-04-11
 */
@Service
public class WmsSkuInfoTypeService extends CrudService<WmsSkuInfoTypeDao, WmsSkuInfoType> {
	
	/**
	 * 获取单条数据
	 * @param wmsSkuInfoType
	 * @return
	 */
	@Override
	public WmsSkuInfoType get(WmsSkuInfoType wmsSkuInfoType) {
		return super.get(wmsSkuInfoType);
	}
	
	/**
	 * 查询分页数据
	 * @param wmsSkuInfoType 查询条件
	 * @param wmsSkuInfoType page 分页对象
	 * @return
	 */
	@Override
	public Page<WmsSkuInfoType> findPage(WmsSkuInfoType wmsSkuInfoType) {
		return super.findPage(wmsSkuInfoType);
	}
	
	/**
	 * 查询列表数据
	 * @param wmsSkuInfoType
	 * @return
	 */
	@Override
	public List<WmsSkuInfoType> findList(WmsSkuInfoType wmsSkuInfoType) {
		return super.findList(wmsSkuInfoType);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wmsSkuInfoType
	 */
	@Override
	@Transactional
	public void save(WmsSkuInfoType wmsSkuInfoType) {
		super.save(wmsSkuInfoType);
	}
	
	/**
	 * 更新状态
	 * @param wmsSkuInfoType
	 */
	@Override
	@Transactional
	public void updateStatus(WmsSkuInfoType wmsSkuInfoType) {
		super.updateStatus(wmsSkuInfoType);
	}
	
	/**
	 * 删除数据
	 * @param wmsSkuInfoType
	 */
	@Override
	@Transactional
	public void delete(WmsSkuInfoType wmsSkuInfoType) {
		super.delete(wmsSkuInfoType);
	}
	
}