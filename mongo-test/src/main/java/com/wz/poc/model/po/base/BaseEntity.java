package com.wz.poc.model.po.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


@Data
@ToString
public class BaseEntity implements Serializable {

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    @Id
    protected String id;

    @ApiModelProperty(hidden = true)
    protected Long gmtCreate;

    @ApiModelProperty(hidden = true)
    protected Long gmtModified;

    @ApiModelProperty(hidden = true)
    protected String createUid;

    @ApiModelProperty(hidden = true)
    protected String createUname;

    @ApiModelProperty(hidden = true)
    protected String modifiedUid;

    @ApiModelProperty(hidden = true)
    protected String modifiedUname;

    @ApiModelProperty(hidden = true)
    protected Boolean isDelete = false;

    @ApiModelProperty(hidden = true)
    protected Boolean enabled = true;

}
