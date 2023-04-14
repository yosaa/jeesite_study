package com.jeesite.modules.wms.config.neo4j;

import com.jeesite.modules.wms.entity.neo4j.MainTagOf3Entity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainTagOf3Repository extends Neo4jRepository<MainTagOf3Entity, Long> {

    List<MainTagOf3Entity> findMainTagById(Long id);
    MainTagOf3Entity findMainTagByTag(String tag);
}
