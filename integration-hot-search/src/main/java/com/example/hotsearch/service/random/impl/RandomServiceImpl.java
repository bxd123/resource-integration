package com.example.hotsearch.service.random.impl;

import cn.hutool.json.JSONUtil;
import com.example.hotsearch.bean.random.RandomAcgData;
import com.example.hotsearch.bean.random.RandomSayingData;
import com.example.hotsearch.constant.UrlConstant;
import com.example.hotsearch.http.HttpClient;
import com.example.hotsearch.service.random.RandomService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.api.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author qiwendong
 */
@Service
@Slf4j
public class RandomServiceImpl extends HttpClient implements RandomService  {

    public RandomServiceImpl(@Autowired Map<String, String> baseUrlMap) {
        super(baseUrlMap.get(UrlConstant.TEN_API));
    }

    /**
     * 获取随机言论
     *
     * @return
     */
    @Override
    public R getRandomSayings() {
        final WebTarget webTarget = this.webTarget.path("v2/yiyan").queryParam("format","json");
        try {
            Response response = asyncGetRequest(webTarget).get();
            String result = response.readEntity(String.class);
            var json = JSONUtil.toBean(result, RandomSayingData.class);
            if ("200".equals(json.getCode())) {
                return R.data(ResultCode.SUCCESS.getCode(), json.getData(), json.getMsg());
            }
            return R.data(ResultCode.FAILURE.getCode(), json.getData(), json.getMsg());
        } catch (ExecutionException | InterruptedException e) {
            return R.fail(e.getMessage());
        }
    }

    /**
     * 获取随机动漫图片
     *
     * @return
     */
    @Override
    public R getRandomAnimeGraph() {
        final WebTarget webTarget = this.webTarget.path("v2/acg").queryParam("format", "json");

        try {
            Response response = this.asyncGetRequest(webTarget).get();

            String result = response.readEntity(String.class);

            var json = JSONUtil.toBean(result, RandomAcgData.class);
            if ("200".equals(json.getCode())) {
                return R.data(ResultCode.SUCCESS.getCode(), json.getData(), json.getMsg());
            }
            return R.data(ResultCode.FAILURE.getCode(), json.getData(), json.getMsg());

        } catch (InterruptedException | ExecutionException e) {
            return R.fail(e.getMessage());
        }
    }
}
