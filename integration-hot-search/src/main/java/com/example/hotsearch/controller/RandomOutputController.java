package com.example.hotsearch.controller;

import com.example.hotsearch.annotion.StartSwaggerScan;
import com.example.hotsearch.service.random.RandomService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiwendong
 */
@RestController("random")
@StartSwaggerScan
@AllArgsConstructor
public class RandomOutputController {

    private RandomService randomService;

    @GetMapping("/saying")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "随机一言", notes = "不需要传参数")
    public R getRandomSaying() {
        return randomService.getRandomSayings();
    }

    @GetMapping("/acg")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "随机动漫图片", notes = "不需要传参数")
    public R getRandomAnimeGraph() {
        return randomService.getRandomAnimeGraph();
    }
}
