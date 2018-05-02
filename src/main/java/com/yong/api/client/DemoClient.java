package com.yong.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "mark-service", path = "demo")
public interface DemoClient {

    @GetMapping("/test")
    String test();

}
