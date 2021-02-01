package com.wz.poc.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liweizhi
 * @date 2021/1/11
 */
@ApiModel(value = "递归的DTO")
@Data
public class CircleDTO {
    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "子元素")
    private CircleDTO subCircle;
}
