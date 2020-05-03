package org.bitsly.benchmark.springboot.mysql;

import lombok.extern.slf4j.Slf4j;
import org.bitsly.benchmark.springboot.mysql.dao.ValidityDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MySQLTest {
    @Resource
    private ValidityDao validityDao;

    @Test
    public void test() {

    }
}
