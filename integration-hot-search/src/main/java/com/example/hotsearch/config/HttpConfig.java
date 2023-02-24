package com.example.hotsearch.config;

import com.example.hotsearch.constant.UrlConstant;
import com.example.hotsearch.properties.HotSearchProperties;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiwendong
 */
@Configuration
@ConditionalOnClass
@EnableConfigurationProperties(value = HotSearchProperties.class)
public class HttpConfig {

    @Bean(initMethod = "check")
    public HotSearchProperties hotSearchProperties () {
        return new HotSearchProperties();
    }

    @Bean("baseUrlMap")
    public Map<String, String> baseUrlMap() {
        Map<String, String> baseMap = new HashMap<>(6);
        for (String baseUrl : hotSearchProperties().getBaseUrls()) {
            if (StringUtil.isBlank(baseUrl)){
                return baseMap;
            }
            if (baseUrl.contains(UrlConstant.TEN_API)) {
                baseMap.put(UrlConstant.TEN_API, baseUrl);
            }
            if (baseUrl.contains(UrlConstant.APIS_JXCXIN)) {
                baseMap.put(UrlConstant.APIS_JXCXIN, baseUrl);
            }
            if (baseUrl.contains(UrlConstant.API_UOMG)) {
                baseMap.put(UrlConstant.API_UOMG, baseUrl);
            }
        }
        return baseMap;
    }
}
