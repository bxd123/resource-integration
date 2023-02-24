package com.example.hotsearch.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;


/**
 * @author qiwendong
 */
@ConfigurationProperties(prefix = HotSearchProperties.HOT_SEARCH_PREFIX)
@Data
public class HotSearchProperties {

    public final static String HOT_SEARCH_PREFIX = "search";
    private String[] baseUrls;

    public void check() {
        Objects.requireNonNull(baseUrls, "config param 'baseUrls' is null.");
    }
}
