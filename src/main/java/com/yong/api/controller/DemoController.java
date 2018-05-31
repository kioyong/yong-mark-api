package com.yong.api.controller;

import com.yong.api.client.DemoClient;
import com.yong.api.client.MarkClient;
import com.yong.api.client.SocketClient;
import com.yong.api.service.DemoService;
import com.yong.api.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/demo")
@AllArgsConstructor
public class DemoController {

    private final LocationService locationService;
    private final MarkClient markClient;
    private final DemoClient demoClient;
    private final DemoService demoService;
    private final SocketClient socketClient;

    {
        log.debug("start init trustAllHosts");
        trustAllHosts();
    }

    @GetMapping("/hello")
    public String hello() {
//        System.setProperty("javax.net.ssl.trustStore","classpath:\\");
//        SSLSocketFactory defaultSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();

        String hello = socketClient.hello();
//        HttpsURLConnection.setDefaultSSLSocketFactory(defaultSSLSocketFactory);
        return hello;
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

    @GetMapping("/test")
    public String testDevTools(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        log.debug("Authorization = {}", authorization);
        return demoClient.test();
    }

    @PostMapping("/group")
    public String testGroup() {
        return demoService.getGroup();
    }

    private static void trustAllHosts() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        }};
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(
                (hostname, sslsession) -> false
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
