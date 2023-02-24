package com.example.hotsearch.controller;

import com.example.hotsearch.annotion.StartSwaggerScan;
import com.example.hotsearch.service.hot.impl.baidu.BaiduSearchHotImpl;
import com.example.hotsearch.service.hot.impl.bzhan.Bilibi1HotSearchServiceImpl;
import com.example.hotsearch.service.hot.impl.bzhan.Bilibi2HotSearchServiceImpl;
import com.example.hotsearch.service.hot.impl.douyin.DouYinSearchHotImpl;
import com.example.hotsearch.service.hot.impl.toutiao.TouTiaoService;
import com.example.hotsearch.service.hot.impl.weibo.WeiBoSearchHotImpl;
import com.example.hotsearch.service.hot.impl.zhihu.ZhiHuSearchHotImpl;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiwendong
 */
@RestController
@RequestMapping("hot")
@StartSwaggerScan
@AllArgsConstructor
public class HotSearchController {
    private Bilibi1HotSearchServiceImpl hotSearchService1;
    private Bilibi2HotSearchServiceImpl hotSearchService2;
    private BaiduSearchHotImpl baiduSearchHot;
    private DouYinSearchHotImpl douYinSearchHot;
    private TouTiaoService touTiaoService;
    private WeiBoSearchHotImpl weiBoSearchHot;
    private ZhiHuSearchHotImpl zhiHuSearchHot;

    @GetMapping("/bibi1")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "B站排行榜前100热搜", notes = "不需要传参数")
    public R getB1HotSearch() {
        return hotSearchService1.searchHot();
    }

    @GetMapping ("/bibi2")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "B站热搜榜", notes = "不需要传参数")
    public R getB2otSearch() {
        return hotSearchService2.searchHot();
    }

    @GetMapping ("/baidu")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "百度热搜榜", notes = "不需要传参数")
    public R getBaiduHotSearch() {
        return baiduSearchHot.searchHot();
    }

    @GetMapping ("douyin")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "抖音热搜榜", notes = "不需要传参数")
    public R getDouYinHotSearch() {
        return douYinSearchHot.searchHot();
    }

    @GetMapping ("/toutiao")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "今日头条热搜榜", notes = "不需要传参数")
    public R getTouTiaoHotSearch() {
        return touTiaoService.searchHot();
    }

    @GetMapping ("/toutiao/new")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "今日头条热点新闻", notes = "不需要传参数")
    public R getTouTiaoNewHotSearch() {
        return touTiaoService.searchHotNew();
    }

    @GetMapping ("/weibo")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "微博热搜榜", notes = "不需要传参数")
    public R getWeiboHotSearch() {
        return weiBoSearchHot.searchHot();
    }

    @GetMapping ("/zhihu")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "知乎热搜榜", notes = "不需要传参数")
    public R getZhiHuHotSearch() {
        return zhiHuSearchHot.searchHot();
    }


}
