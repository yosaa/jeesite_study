package com.jeesite.modules.wms.entity.neo4j;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;


@Node("相关企业")
@Data
public class CompanyOf5Entity {
    @Id
    @GeneratedValue
    private Long id;
    private String tag;
    private String info;
    public CompanyOf5Entity(String tag , String info) {
        this.tag = tag;
        this.info = info;
    }

}

