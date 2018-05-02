package com.yong.api.controller;

import com.yong.api.client.MarkClient;
import com.yong.mark.model.Mark;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LiangYong
 * @date 2017/12/10
 */
@Slf4j
@RestController
@RequestMapping("/mark")
@AllArgsConstructor
public class MarkController {

    private final MarkClient client;

    @GetMapping("/")
    public String hello() {
        return client.hello();
    }

    @GetMapping("/mark")
    public List<Mark> getAllMrk() {
        return client.getAllMark();
    }

}
