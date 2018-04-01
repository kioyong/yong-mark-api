package com.yong.api.service;

import com.yong.api.client.LocationClient;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationClient locationClient;

    @Override
    public String getHello() {
        return locationClient.getHello();
    }

    @Override
    @Async
    public CompletableFuture<String> getLocation() {
        return CompletableFuture.completedFuture(locationClient.getLocation());
    }
}
