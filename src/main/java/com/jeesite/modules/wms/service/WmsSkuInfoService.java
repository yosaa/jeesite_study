package com.jeesite.modules.wms.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.wms.entity.WmsSkuInfo;
import com.jeesite.modules.wms.dao.WmsSkuInfoDao;
import com.jeesite.common.service.ServiceException;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.validator.ValidatorUtils;
import com.jeesite.common.utils.excel.ExcelImport;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 商品详情Service
 * @author yosaa
 * @version 2023-04-11
 */
@Service
public class WmsSkuInfoService extends CrudService<WmsSkuInfoDao, WmsSkuInfo> {
	
	/**
	 * 获取单条数据
	 * @param wmsSkuInfo
	 * @return
	 */
	@Override
	public WmsSkuInfo get(WmsSkuInfo wmsSkuInfo) {
		return super.get(wmsSkuInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param wmsSkuInfo 查询条件
	 * @param wmsSkuInfo page 分页对象
	 * @return
	 */
	@Override
	public Page<WmsSkuInfo> findPage(WmsSkuInfo wmsSkuInfo) {
		return super.findPage(wmsSkuInfo);
	}
	
	/**
	 * 查询列表数据
	 * @param wmsSkuInfo
	 * @return
	 */
	@Override
	public List<WmsSkuInfo> findList(WmsSkuInfo wmsSkuInfo) {
		return super.findList(wmsSkuInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wmsSkuInfo
	 */
	@Override
	@Transactional
	public void save(WmsSkuInfo wmsSkuInfo) {
		super.save(wmsSkuInfo);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(wmsSkuInfo, wmsSkuInfo.getId(), "wmsSkuInfo_image");
	}

	/**
	 * 导入数据
	 * @param file 导入的数据文件
	 */
	@Transactional
	public String importData(MultipartFile file) {
		if (file == null){
			throw new ServiceException(text("请选择导入的数据文件！"));
		}
		int successNum = 0; int failureNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		try(ExcelImport ei = new ExcelImport(file, 2, 0)){
			List<WmsSkuInfo> list = ei.getDataList(WmsSkuInfo.class);
			for (WmsSkuInfo wmsSkuInfo : list) {
				try{
					ValidatorUtils.validateWithException(wmsSkuInfo);
					this.save(wmsSkuInfo);
					successNum++;
					successMsg.append("<br/>" + successNum + "、编号 " + wmsSkuInfo.getId() + " 导入成功");
				} catch (Exception e) {
					failureNum++;
					String msg = "<br/>" + failureNum + "、编号 " + wmsSkuInfo.getId() + " 导入失败：";
					if (e instanceof ConstraintViolationException){
						ConstraintViolationException cve = (ConstraintViolationException)e;
						for (ConstraintViolation<?> violation : cve.getConstraintViolations()) {
							msg += Global.getText(violation.getMessage()) + " ("+violation.getPropertyPath()+")";
						}
					}else{
						msg += e.getMessage();
					}
					failureMsg.append(msg);
					logger.error(msg, e);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			failureMsg.append(e.getMessage());
			return failureMsg.toString();
		}
		if (failureNum > 0) {
			failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
			throw new ServiceException(failureMsg.toString());
		}else{
			successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
		}
		return successMsg.toString();
	}
	
	/**
	 * 更新状态
	 * @param wmsSkuInfo
	 */
	@Override
	@Transactional
	public void updateStatus(WmsSkuInfo wmsSkuInfo) {
		super.updateStatus(wmsSkuInfo);
	}
	
	/**
	 * 删除数据
	 * @param wmsSkuInfo
	 */
	@Override
	@Transactional
	public void delete(WmsSkuInfo wmsSkuInfo) {
		super.delete(wmsSkuInfo);
	}
	
}