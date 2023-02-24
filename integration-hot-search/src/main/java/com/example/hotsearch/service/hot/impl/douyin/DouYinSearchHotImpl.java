package com.example.hotsearch.service.hot.impl.douyin;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.example.hotsearch.bean.hot.DataDetail;
import com.example.hotsearch.bean.hot.Detail1;
import com.example.hotsearch.bean.hot.Detail2;
import com.example.hotsearch.bean.hot.HotData;
import com.example.hotsearch.constant.UrlConstant;
import com.example.hotsearch.http.HttpClient;
import com.example.hotsearch.service.hot.HotSearchService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.api.ResultCode;
import org.springblade.core.tool.utils.ObjectUtil;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author qiwendong
 */
@Service
@Slf4j
public class DouYinSearchHotImpl extends HttpClient implements HotSearchService {

    public DouYinSearchHotImpl(@Autowired Map<String, String> baseUrlMap) {
        super(baseUrlMap.get(UrlConstant.TEN_API));
    }

    /**
     * 调接口查询热点搜索
     *
     * @return
     */
    @Override
    public R searchHot() {
        final WebTarget webTarget = this.webTarget.path("v2/douyinhot");
        try {
            Response response = asyncGetRequest(webTarget).get();
            String result = response.readEntity(String.class);
            val json = JSONUtil.toBean(result, HotData.class);

            if ("200".equals(json.getCode())) {
                return R.data(ResultCode.SUCCESS.getCode(), json.getData().stream().map(dataDetail ->
                        {
                            if (ObjectUtil.isEmpty(dataDetail.getName())) {
                                Detail1 detail1 = new Detail1();
                                BeanUtil.copyProperties(dataDetail, detail1);
                                return detail1;
                            } else {
                                Detail2 detail2 = new Detail2();
                                BeanUtil.copyProperties(dataDetail, detail2);
                                return detail2;
                            }
                        }
                        ).collect(Collectors.toList()), json.getMsg());
            }
            return R.data(ResultCode.FAILURE.getCode(), json, json.getMsg());
        } catch (InterruptedException | ExecutionException e) {
            return R.fail(e.getMessage());
        }
    }
}
