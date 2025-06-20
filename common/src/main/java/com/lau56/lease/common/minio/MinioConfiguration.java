package com.lau56.lease.common.minio;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Michael
 * @date 2025/6/20 00:26
 * @description Minio配置类
 */
//@ConfigurationPropertiesScan("com.lau56.lease.common.minio")
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfiguration {
    @Autowired
    private MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}

