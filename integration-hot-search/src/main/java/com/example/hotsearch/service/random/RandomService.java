package com.example.hotsearch.service.random;

import org.springblade.core.tool.api.R;

/**
 * @author qiwendong
 */
public interface RandomService {

    /**
     * 获取随机言论
     * @return
     */
    R getRandomSayings();

    /**
     * 获取随机动漫图片
     * @return
     */
    R getRandomAnimeGraph();
}
