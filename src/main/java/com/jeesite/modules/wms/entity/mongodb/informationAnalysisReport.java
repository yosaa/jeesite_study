package com.jeesite.modules.wms.entity.mongodb;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@Document("information_analysis_report")
public class informationAnalysisReport {
    @Id
    private String id;
    //标题
    private String title;
    //原链接
    private String url;
    //发布时间
    private Date time;
    //发布机构
    private String publisher;
    //是否收费  0不收费  1收费
    private Integer price;
    //行业
    private Integer[] industry;
}
