package com.envelope.back.pojo;

import com.baomidou.mybatisplus.annotation.*;
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
@ApiModel(value = "用户角色表")
public class SysUserRole implements Serializable {

    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(value = "唯一标识ID", name = "id", dataType = "String", example = "UUID", required = true)
    private String id;

    @TableField(value = "userId")
    @ApiModelProperty(value = "用户id", name = "userId", dataType = "String", example = "UUID", required = true)
    private String userId;

    @TableField(value = "roleId")
    @ApiModelProperty(value = "角色id", name = "roleId", dataType = "String", example = "UUID", required = true)
    private String roleId;

    @TableField(value = "updateTime", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间, 由mybatis-plus自动生成", name = "createTime", dataType = "Date")
    private Date createTime;

    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间, 由mybatis-plus自动生成", name = "updateTime", dataType = "Date")
    private Date updateTime;

    @TableLogic
    @TableField(value = "deleted", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "逻辑删除标识, 由mybatis-plus自动生成", name = "deleted", dataType = "Integer")
    private Integer deleted;

    @Version
    @TableField(value = "version", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "版本号, 由mybatis-plus自动生成", name = "version", dataType = "Integer")
    private Integer version;
}
