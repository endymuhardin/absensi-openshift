package com.artivisi.absensi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class AplikasiAbsensi {
    public static void main(String[] args) {
        SpringApplication.run(AplikasiAbsensi.class, args);
    }
}
