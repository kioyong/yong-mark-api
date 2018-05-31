package com.yong.api.client;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mark-service", path = "demo")
public interface DemoClient {

    @GetMapping("/test")
    String test();

    @CacheEvict(value="group", allEntries=true)
    @PostMapping("/group")
    String getGroup();
}
