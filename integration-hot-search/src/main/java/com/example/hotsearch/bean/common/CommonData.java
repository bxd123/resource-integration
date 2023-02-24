package com.example.hotsearch.bean.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qiwendong
 */
@Data
public class CommonData implements Serializable {
    @ApiModelProperty(value = "code", name = "code", dataType = "String")
    private String code;
    @ApiModelProperty(value = "msg", name = "msg", dataType = "String")
    private String msg;
    @ApiModelProperty(value = "time", name = "time", dataType = "String")
    private String time;
    /**
     * 行数
     */
    @ApiModelProperty(value = "条数", name = "count", dataType = "String")
    private String count;
}
