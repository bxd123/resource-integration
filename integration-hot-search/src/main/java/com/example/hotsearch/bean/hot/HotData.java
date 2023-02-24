package com.example.hotsearch.bean.hot;

import com.example.hotsearch.bean.common.CommonData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author qiwendong
 */
@Data
@ToString
@ApiModel(value = "HotData", description = "热搜信息")
public class HotData extends CommonData {
    @ApiModelProperty(value = "细节信息", name = "data", dataType = "List")
    private List<DataDetail> data;
}
