package com.spring.boot.serverforchess.rest.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan(basePackages = "com.spring.boot.serverforchess.rest")
@EnableWebMvc

public class RestConfig { }
