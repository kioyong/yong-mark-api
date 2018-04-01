package com.yong.api.service;

import java.util.concurrent.CompletableFuture;

public interface LocationService {
    String getHello();
    CompletableFuture<String> getLocation();
}
