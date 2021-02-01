package com.wz.poc.controller;

import com.wz.poc.model.dto.CircleDTO;
import com.wz.poc.model.vo.CircleVO;
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

    @GetMapping("/circle/dto")
    public String circleDto(CircleDTO dtp) {
        return "/circle/dto";
    }

    @GetMapping("/circle/vo")
    public CircleVO circleVo() {
        return new CircleVO();
    }
}
