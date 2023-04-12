package com.jeesite.modules.wms.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.wms.entity.WmsSkuInfo;

/**
 * 商品详情DAO接口
 * @author yosaa
 * @version 2023-04-11
 */
@MyBatisDao
public interface WmsSkuInfoDao extends CrudDao<WmsSkuInfo> {
	
}