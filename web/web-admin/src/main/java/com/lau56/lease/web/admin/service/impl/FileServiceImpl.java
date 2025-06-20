package com.lau56.lease.web.admin.service.impl;

import com.lau56.lease.common.minio.MinioProperties;
import com.lau56.lease.web.admin.service.FileService;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioProperties properties;

    @Override
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(properties.getBucketName()).build());
            if (!bucketExists) {
                // 如果存储桶不存在，则创建它
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(properties.getBucketName())
                        .build());
                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                        .bucket(properties.getBucketName())
                        .config(createBucketPolicyConfig(properties.getBucketName()))
                        .build());
            }
            String filename = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(properties.getBucketName())
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .object(filename)
                    .contentType(file.getContentType())
                    .build());
            // 返回文件的访问URL
            return String.join("/", properties.getEndpoint(), properties.getBucketName(), filename);

    }

    /**
     * 创建存储桶策略配置
     *
     * @param bucketName
     * @return
     */
    private String createBucketPolicyConfig(String bucketName) {

        return """
                {
                  "Statement" : [ {
                    "Action" : "s3:GetObject",
                    "Effect" : "Allow",
                    "Principal" : "*",
                    "Resource" : "arn:aws:s3:::%s/*"
                  } ],
                  "Version" : "2012-10-17"
                }
                """.formatted(bucketName);
    }


}
