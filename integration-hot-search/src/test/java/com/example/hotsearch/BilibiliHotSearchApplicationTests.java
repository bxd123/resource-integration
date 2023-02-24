package com.example.hotsearch;

import cn.hutool.json.JSONUtil;
import com.example.hotsearch.service.hot.impl.baidu.BaiduSearchHotImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.ws.rs.client.WebTarget;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class BilibiliHotSearchApplicationTests {

    @Autowired
    private BaiduSearchHotImpl baiduSearchHot;
    @Test
    void contextLoads() {
    }

    @Test
    public void textBean() throws ExecutionException, InterruptedException {

        final WebTarget webTarget = baiduSearchHot.getWebTarget().path("v2/baiduhot");

//        String reslut = baiduSearchHot.asyncGetRequest(webTarget).get().readEntity(String.class);

//        HotData1 hotData1 = JSONUtil.toBean(reslut, HotData1.class);
//
//        System.out.println(JSONUtil.toJsonStr(hotData1.getData()));
    }

}
