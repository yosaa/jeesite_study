package com.jeesite.modules.wms.entity.neo4j;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;


@Node("建设方向")
@Data
public class TypeOf1Entity {
    @Id
    @GeneratedValue
    private Long id;
    private String tag;
    private String info;
    public TypeOf1Entity(String tag , String info) {
        this.id = null;// 生成node时自动生成
        this.tag = tag;
        this.info = info;
    }

    @Relationship(type = "建设重点", direction = Relationship.Direction.INCOMING)
    private List<PointOf2Entity> include = new ArrayList<>();

}

