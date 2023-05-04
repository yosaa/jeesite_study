package com.jeesite.modules.wms.web;

import com.jeesite.modules.wms.config.Result;
import com.jeesite.modules.wms.entity.mongodb.informationAnalysisReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "mongodb")
public class MongoDBData {
    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping(value = "find")
    @ResponseBody
    public Result<?> mongdb() {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "time")).limit(3);
        List<informationAnalysisReport> informationAnalysisReportsList = mongoTemplate.find(query,informationAnalysisReport.class);

        return Result.ok(informationAnalysisReportsList);

    }
}
