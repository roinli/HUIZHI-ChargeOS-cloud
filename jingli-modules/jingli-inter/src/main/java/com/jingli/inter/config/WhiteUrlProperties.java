package com.jingli.inter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "white.url")
@Data
public class WhiteUrlProperties
{

    private List<String> urls = new ArrayList<>();


}
