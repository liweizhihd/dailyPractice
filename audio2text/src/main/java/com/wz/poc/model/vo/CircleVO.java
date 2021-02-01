package com.wz.poc.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liweizhi
 * @date 2021/1/11
 */
@ApiModel(value = "递归的VO")
@Data
public class CircleVO {
    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "子元素")
    private CircleVO subCircle;
}
