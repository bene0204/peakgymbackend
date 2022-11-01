package com.benem.peakgym;

import com.benem.peakgym.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class PeakgymApplication {
    public static void main(String[] args) {


        SpringApplication.run(PeakgymApplication.class, args);
    }
}
