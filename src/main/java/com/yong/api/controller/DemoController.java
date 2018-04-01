package com.yong.api.controller;

import com.yong.api.client.LocationClient;
import com.yong.api.client.MarkClient;
import com.yong.api.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RestController
@RequestMapping("/demo")
@AllArgsConstructor
public class DemoController {

    private final LocationService locationService;
    private final MarkClient markClient;

    @GetMapping("/hello")
    public String hello(){
        return locationService.getHello();
    }

    //bad
    @GetMapping("/location")
    public String getLocation() throws InterruptedException, ExecutionException {
        log.debug("start get location info.");
        CompletableFuture<String> location = locationService.getLocation();
        log.debug("start get mark info.");
        String hello = markClient.hello();
        CompletableFuture.allOf(location).join();
        return location.get().concat(hello);
    }

}
