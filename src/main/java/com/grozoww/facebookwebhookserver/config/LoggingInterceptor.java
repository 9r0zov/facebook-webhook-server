package com.grozoww.facebookwebhookserver.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Log4j2
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution exec) throws IOException {

        log.info("➡️  {} {}", request.getMethod(), request.getURI());
        log.info("Headers  : {}", request.getHeaders());
        log.info("Body out : {}", new String(body, StandardCharsets.UTF_8));

        ClientHttpResponse resp = exec.execute(request, body);

        String respBody = StreamUtils.copyToString(resp.getBody(), StandardCharsets.UTF_8);
        log.info("⬅️  Status  : {}", resp.getStatusCode());
        log.info("Body in  : {}", respBody);

        return resp;
    }
}
