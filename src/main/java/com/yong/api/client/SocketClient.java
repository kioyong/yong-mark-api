package com.yong.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "yong",url = "https://yong-socket:9009")
public interface SocketClient {

    @GetMapping("/hello")
    String hello();

}
