package com.envelope.back.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "日志实体类", description = "定义了日志的各个属性，配合日志切面使用")
public class SysLog implements Serializable {

    private static final long serialVersionUID = -6309732882044872298L;

    @ApiModelProperty(value = "日志ID", name = "logId", dataType = "UUID", required = true)
    private String logId;

    @ApiModelProperty(value = "用户ID", name = "userId", dataType = "UUID", required = true)
    private String userId;

    @ApiModelProperty(value = "包名", name = "packageName", dataType = "String")
    private String packageName;

    @ApiModelProperty(value = "执行时间", name = "executionTime", dataType = "Long")
    private Long executionTime;

    @ApiModelProperty(value = "方法名", name = "method", dataType = "String")
    private String method;

    @ApiModelProperty(value = "参数", name = "params", dataType = "String")
    private String params;

    @ApiModelProperty(value = "描述", name = "desc", dataType = "String")
    private String desc;

    @ApiModelProperty(value = "创建时间", name = "createTime", dataType = "Date")
    private Date createTime;

}
