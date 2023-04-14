package com.jeesite.modules.wms.config.neo4j;

import com.jeesite.modules.wms.entity.neo4j.PointOf2Entity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PonitOf2Repository extends Neo4jRepository<PointOf2Entity, Long> {

    List<PointOf2Entity> findTagById(Long id);
    PointOf2Entity findTagByTag(String tag);
}
