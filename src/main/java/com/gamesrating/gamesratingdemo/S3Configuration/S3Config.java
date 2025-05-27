package com.gamesrating.gamesratingdemo.S3Configuration;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.access.key}")
    private String accesKeyId;

    @Value("${aws.secret.key}")
    private String secretKeyString;

    @Value("${aws.region}")
    private String region;

    @Bean
    public S3Client s3Client() {
        System.out.println("ESTA ES LA ACCES KEY : :::::::::::::::: " + accesKeyId);
        System.out.println("ESTA ES LA SECRET KEY ::::::::::::::::" + secretKeyString);
        AwsCredentials awsc = AwsBasicCredentials.create(accesKeyId, secretKeyString);

        return S3Client.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create("https://s3.us-east-2.amazonaws.com"))
                .credentialsProvider(StaticCredentialsProvider.create(awsc))
                .build();
    }

}
