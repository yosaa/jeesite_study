package com.jeesite.modules.wms.entity;

import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 商品类别Entity
 * @author yosaa
 * @version 2023-04-11
 */
@Table(name="wms_sku_info_type", alias="a", label="商品类别信息", columns={
		@Column(name="type_id", attrName="typeId", label="类型id", isPK=true),
		@Column(name="type_name", attrName="typeName", label="类型名称", queryType=QueryType.LIKE),
		@Column(name="status", attrName="status", label="状态", comment="状态（0：正常，1：删除，2.禁用）", isUpdate=false),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false, isUpdateForce=true),
	}, orderBy="a.type_id DESC"
)
public class WmsSkuInfoType extends DataEntity<WmsSkuInfoType> {
	
	private static final long serialVersionUID = 1L;
	private String typeId;		// 类型id
	private String typeName;		// 类型名称

	public WmsSkuInfoType() {
		this(null);
	}
	
	public WmsSkuInfoType(String id){
		super(id);
	}
	
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	@Size(min=0, max=50, message="类型名称长度不能超过 50 个字符")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}