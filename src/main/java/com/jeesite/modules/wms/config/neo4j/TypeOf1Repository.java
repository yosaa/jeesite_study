package com.jeesite.modules.wms.config.neo4j;

import com.jeesite.modules.wms.entity.neo4j.TypeOf1Entity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeOf1Repository extends Neo4jRepository<TypeOf1Entity, Long> {

    List<TypeOf1Entity> findTypeById(Long id);
    TypeOf1Entity findTypeByTag(String tag);
}
