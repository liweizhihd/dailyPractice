package com.wz.poc.controller;

import com.wz.poc.audio2text.Audio2TextHelper;
import com.wz.poc.model.vo.ResultWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liweizhi
 * @date 2021/1/6
 */
@RestController
@RequestMapping("/audio")
public class Audio2textController {

    @Resource
    private Audio2TextHelper audio2TextHelper;

    @GetMapping("/toText")
    public ResultWrapper<String> toText(String audioUrl) {
        return ResultWrapper.getSuccessResultView(audio2TextHelper.convert(audioUrl));
    }
}
