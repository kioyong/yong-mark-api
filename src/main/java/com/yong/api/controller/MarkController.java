package com.yong.api.controller;

import com.yong.api.client.MarkClient;
import com.yong.mark.model.Mark;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiangYong
 * @date 2017/12/10
 */
@RestController
@RequestMapping("/mark")
@AllArgsConstructor
public class MarkController{

    private final MarkClient client;

    @GetMapping("/")
    public String hello(){
        return client.hello();
    }

    @GetMapping("/mark")
    public List<Mark> getAllMrk(){
        return client.getAllMark();
    }

    @GetMapping("/test")
    public String testDevTools(){
        return "hello devTools->7";
    }
}
