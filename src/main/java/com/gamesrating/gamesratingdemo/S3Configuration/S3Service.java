package com.gamesrating.gamesratingdemo.S3Configuration;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

    @Value("${aws.bucketName}")
    private String bucketName;

    private final S3Client s3client;

    public S3Service(S3Client s3client) {
        this.s3client = s3client;
    }

    public String upload(MultipartFile file) throws java.io.IOException {

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .contentType(file.getContentType())
                .build();

        System.out.println(request);

        s3client.putObject(request, RequestBody.fromBytes(file.getBytes()));
        return "https://" + bucketName + ".s3.us-east-2.amazonaws.com/" + filename;
    }

}
