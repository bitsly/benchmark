package org.bitsly.benchmark.springboot.assembly.controller;

import org.bitsly.benchmark.springboot.assembly.mapper.ValidityMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestRestController {
    @Resource
    private ValidityMapper mapper;

    @GetMapping("/get")
    public Object get() {
        return mapper.valid();
    }
}
