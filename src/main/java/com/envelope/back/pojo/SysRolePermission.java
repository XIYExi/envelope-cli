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
@ApiModel(value = "角色权限表")
public class SysRolePermission implements Serializable {

    @TableId(value = "id")
    @ApiModelProperty(value = "唯一表示", name = "id" , dataType = "UUID", required = true)
    private String id;

    @TableField(value = "roleId")
    @ApiModelProperty(value = "roleId", name = "roleId", dataType = "UUID")
    private String roleId;

    @TableField(value = "permissionId")
    @ApiModelProperty(value = "permissionId", name = "permissionId", dataType = "UUID")
    private String permissionId;


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
