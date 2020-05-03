package org.bitsly.benchmark.springboot.mysql.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ValidityDao {
    String valid();
}
