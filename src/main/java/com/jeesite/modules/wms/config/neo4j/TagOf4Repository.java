package com.jeesite.modules.wms.config.neo4j;

import com.jeesite.modules.wms.entity.neo4j.TagOf4Entity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagOf4Repository extends Neo4jRepository<TagOf4Entity, Long> {

    List<TagOf4Entity> findTagById(Long id);
    TagOf4Entity findTagByTag(String tag);
}
