package org.bitsly.benchmark.springboot.assembly.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ValidityMapper {
    String valid();
}
