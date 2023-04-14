package com.jeesite.modules.wms.entity.neo4j;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;


@Node("细分类目")
@Data
public class TagOf4Entity {
    @Id
    @GeneratedValue
    private Long id;
    private String tag;
    private String info;
    public TagOf4Entity(String tag , String info) {
        this.tag = tag;
        this.info = info;
    }
    @Relationship(type = "包含企业", direction = Relationship.Direction.INCOMING)
    private List<CompanyOf5Entity> include = new ArrayList<>();
}

