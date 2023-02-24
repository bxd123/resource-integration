package com.example.hotsearch.bean.hot;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qiwendong
 */
@Data
@ApiModel(value = "Detail1", description = "数据详情 ")
public class Detail1 implements Serializable {
    @ApiModelProperty(value = "标题", name = "title", dataType = "String")
    private String title;
    @ApiModelProperty(value = "热度", name = "heat", dataType = "String")
    private String  heat;
    @ApiModelProperty(value = "链接", name = "link", dataType = "String")
    private String  link;
}
