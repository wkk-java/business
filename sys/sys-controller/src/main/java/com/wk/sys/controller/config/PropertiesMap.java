package com.wk.sys.controller.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: vince
 * create at: 2021/1/10 下午1:19
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "sys")
@Data
public class PropertiesMap {
    private String mask;
}
