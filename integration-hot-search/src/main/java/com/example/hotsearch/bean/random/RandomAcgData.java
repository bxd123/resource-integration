package com.example.hotsearch.bean.random;

import com.example.hotsearch.bean.common.CommonData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qiwendong
 */
@Data
@ApiModel(value = "RandomAcgData", description = "数据详情 ")
public class RandomAcgData extends CommonData {
    @ApiModelProperty(value = "细节信息", name = "data", dataType = "List")
    private Data data;

    @lombok.Data
    private class Data {
        private String width;
        private String height;
        private String url;
    }
}
