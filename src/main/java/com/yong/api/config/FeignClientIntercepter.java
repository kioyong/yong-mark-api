package com.yong.api.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
@AllArgsConstructor
public class FeignClientIntercepter implements RequestInterceptor {

    private HttpServletRequest request;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String authorization = request.getHeader("Authorization");
        requestTemplate.header("Authorization", authorization);
    }
}
