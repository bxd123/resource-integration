package com.example.hotsearch.bean.random;

import com.example.hotsearch.bean.common.CommonData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qiwendong
 */
@Data
@ApiModel(value = "RandomSayingData", description = "数据详情 ")
public class RandomSayingData extends CommonData {
    @ApiModelProperty(value = "细节信息", name = "data", dataType = "List")
    private Data data;

    @lombok.Data
    private class Data {
        @ApiModelProperty(value = "id", name = "id", dataType = "String")
        private String id;
        @ApiModelProperty(value = "内容", name = "hitokoto", dataType = "String")
        private String hitokoto;
        @ApiModelProperty(value = "分类", name = "cat", dataType = "String")
        private String cat;
        @ApiModelProperty(value = "分类名称", name = "catname", dataType = "String")
        private String catname;
        @ApiModelProperty(value = "作者", name = "author", dataType = "String")
        private String author;
        @ApiModelProperty(value = "来源", name = "source", dataType = "String")
        private String source;
        @ApiModelProperty(value = "发布时间", name = "date", dataType = "String")
        private String date;

    }
}
