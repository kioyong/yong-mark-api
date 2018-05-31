package com.yong.api.service;

import com.yong.api.client.DemoClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class DemoServiceImpl implements DemoService {

    private final DemoClient demoClient;

    @Override
    @CacheEvict(value="group", allEntries=true)
    public String getGroup() {
        return demoClient.getGroup();
    }
}
