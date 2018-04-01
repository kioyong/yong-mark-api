package com.yong.api.client;

import com.yong.mark.model.Mark;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author LiangYong
 * @date 2017/12/10
 */

@FeignClient(name = "mark-service", path = "mark", fallback = MarkClientFallback.class)
public interface MarkClient {
    @GetMapping("/hello")
    String hello();

    @GetMapping("/mark")
    List<Mark> getAllMark();
}

@Component
class MarkClientFallback implements MarkClient {

    @Override
    public String hello() {
        return "fallback for MarkClient";
    }

    @Override
    public List<Mark> getAllMark() {
        return null;
    }
}
