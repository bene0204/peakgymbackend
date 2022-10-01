package com.benem.peakgym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PeakgymApplication {
    public static void main(String[] args) {
        SpringApplication.run(PeakgymApplication.class, args);
    }
}
