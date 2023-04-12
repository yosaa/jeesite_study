package com.jeesite.modules.wms.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.wms.entity.WmsSkuInfoType;

/**
 * 商品类别DAO接口
 * @author yosaa
 * @version 2023-04-11
 */
@MyBatisDao
public interface WmsSkuInfoTypeDao extends CrudDao<WmsSkuInfoType> {
	
}