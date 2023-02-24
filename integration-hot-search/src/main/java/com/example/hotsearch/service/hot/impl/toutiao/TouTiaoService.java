package com.example.hotsearch.service.hot.impl.toutiao;

import com.example.hotsearch.service.hot.HotSearchService;
import org.springblade.core.tool.api.R;

/**
 * @author qiwendong
 */
public interface TouTiaoService extends HotSearchService {

    /**
     * 今日头条热搜榜
     *
     * @return
     */
    @Override
    R searchHot();

    /**
     * 今日头条热点新闻
     * @return
     */
    R searchHotNew();
}
