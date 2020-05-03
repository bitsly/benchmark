package org.bitsly.benchmark.springboot.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @PutMapping("/rest")
    public Object get() {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        res.add(2);
        return res;
    }

    @PostMapping("/rest")
    public Object post() {
        return "ok";
    }
}
