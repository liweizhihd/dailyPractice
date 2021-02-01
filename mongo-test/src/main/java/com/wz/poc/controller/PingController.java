package com.wz.poc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liweizhi
 * @date 2020/12/30
 */
@RestController
public class PingController {

    @GetMapping("/ping")
    public String pong() {
        return "pong";
    }

}
