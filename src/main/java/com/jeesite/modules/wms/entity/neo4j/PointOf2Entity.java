package com.jeesite.modules.wms.entity.neo4j;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;


@Node("建设重点")
@Data
public class PointOf2Entity {
    @Id
    @GeneratedValue
    private Long id;
    private String tag;
    private String info;
    public PointOf2Entity(String tag , String info) {
        this.tag = tag;
        this.info = info;
    }
    @Relationship(type = "类目", direction = Relationship.Direction.INCOMING)
    private List<MainTagOf3Entity> include = new ArrayList<>();
}

