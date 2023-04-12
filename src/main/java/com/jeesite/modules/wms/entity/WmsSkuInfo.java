package com.jeesite.modules.wms.entity;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.common.utils.excel.annotation.ExcelField;
import com.jeesite.common.utils.excel.annotation.ExcelField.Align;
import com.jeesite.common.utils.excel.annotation.ExcelFields;

/**
 * 商品详情Entity
 * @author yosaa
 * @version 2023-04-11
 */
@Table(name="wms_sku_info", alias="a", label="商品详情信息", columns={
		@Column(name="id", attrName="id", label="商品id", isPK=true),
		@Column(name="sku_id", attrName="skuId", label="商品编码"),
		@Column(name="sku_name", attrName="skuName", label="商品名称", queryType=QueryType.LIKE),
		@Column(name="price", attrName="price", label="单价", isQuery=false, isUpdateForce=true),
		@Column(name="selling_price", attrName="sellingPrice", label="售价", isQuery=false, isUpdateForce=true),
		@Column(name="cost_price", attrName="costPrice", label="成本价", isQuery=false, isUpdateForce=true),
		@Column(name="desc", attrName="desc", label="描述", isQuery=false),
		@Column(name="image", attrName="image", label="商品图", isQuery=false),
		@Column(name="sku_type_id", attrName="skuTypeId", label="类别id"),
		@Column(name="status", attrName="status", label="状态", comment="状态（0：正常，1：删除，2.禁用）", isUpdate=false),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false, isUpdateForce=true),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false, isUpdateForce=true),
	}, orderBy="a.update_date DESC"
)
public class WmsSkuInfo extends DataEntity<WmsSkuInfo> {
	
	private static final long serialVersionUID = 1L;
	private String skuId;		// 商品编码
	private String skuName;		// 商品名称
	private Double price;		// 单价
	private Double sellingPrice;		// 售价
	private Double costPrice;		// 成本价
	private String desc;		// 描述
	private String image;		// 商品图
	private String skuTypeId;		// 类别id

	@ExcelFields({
		@ExcelField(title="商品id", attrName="id", align=Align.CENTER, sort=10),
		@ExcelField(title="商品编码", attrName="skuId", align=Align.CENTER, sort=20),
		@ExcelField(title="商品名称", attrName="skuName", align=Align.CENTER, sort=30),
		@ExcelField(title="单价", attrName="price", align=Align.CENTER, sort=40),
		@ExcelField(title="售价", attrName="sellingPrice", align=Align.CENTER, sort=50),
		@ExcelField(title="成本价", attrName="costPrice", align=Align.CENTER, sort=60),
		@ExcelField(title="描述", attrName="desc", align=Align.CENTER, sort=70),
		@ExcelField(title="商品图", attrName="image", align=Align.CENTER, sort=80),
		@ExcelField(title="类别id", attrName="skuTypeId", align=Align.CENTER, sort=90),
		@ExcelField(title="状态", attrName="status", dictType="sys_search_status", align=Align.CENTER, sort=100),
		@ExcelField(title="创建人", attrName="createBy", align=Align.CENTER, sort=110),
		@ExcelField(title="更新时间", attrName="updateDate", align=Align.CENTER, sort=120),
		@ExcelField(title="创建时间", attrName="createDate", align=Align.CENTER, sort=130),
	})
	public WmsSkuInfo() {
		this(null);
	}
	
	public WmsSkuInfo(String id){
		super(id);
	}
	
	@Size(min=0, max=50, message="商品编码长度不能超过 50 个字符")
	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	
	@NotBlank(message="商品名称不能为空")
	@Size(min=0, max=50, message="商品名称长度不能超过 50 个字符")
	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	
	@Size(min=0, max=100, message="描述长度不能超过 100 个字符")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Size(min=0, max=255, message="商品图长度不能超过 255 个字符")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@NotBlank(message="类别id不能为空")
	@Size(min=0, max=1, message="类别id长度不能超过 1 个字符")
	public String getSkuTypeId() {
		return skuTypeId;
	}

	public void setSkuTypeId(String skuTypeId) {
		this.skuTypeId = skuTypeId;
	}
	
}