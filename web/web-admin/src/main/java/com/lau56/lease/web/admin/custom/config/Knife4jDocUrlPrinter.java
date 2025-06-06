package com.lau56.lease.web.admin.custom.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Knife4jDocUrlPrinter implements ApplicationRunner {
    @Value("${server.port:8080}")
    private int port;

    @Value("${server.servlet.context-path:}")
    private String contextPath;
    @Override
    public void run(ApplicationArguments args) {
        String path = (contextPath == null || contextPath.isEmpty()) ? "" : contextPath;
        String docUrl = String.format("http://localhost:%d%s/doc.html", port, path);
        log.info("--------------------------------------------------");
        log.info("Knife4j 文档地址: {}", docUrl);
        log.info("--------------------------------------------------");

    }
}