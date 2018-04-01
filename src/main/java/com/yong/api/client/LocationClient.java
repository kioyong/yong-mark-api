package com.yong.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "location-service", path = "/location", fallback = LocationClientFallback.class)
public interface LocationClient {

    @GetMapping("/location")
    String getLocation();

    @GetMapping
    String getHello();
}

@Component
class LocationClientFallback implements LocationClient {

    @Override
    public String getLocation() {
        return "location fallback";
    }

    @Override
    public String getHello() {
        return "location fallback";
    }
}
