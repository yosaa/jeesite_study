package com.jeesite.modules.wms.config.neo4j;

import com.jeesite.modules.wms.entity.neo4j.CompanyOf5Entity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyOf5Repository extends Neo4jRepository<CompanyOf5Entity, Long> {

    List<CompanyOf5Entity> findCompanyById(Long id);
    CompanyOf5Entity findCompanyByTag(String tag);
}
