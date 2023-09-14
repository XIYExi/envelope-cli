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
@ApiModel(value = "权限表")
public class Permission implements Serializable {

    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(value = "唯一标识ID", name = "id", dataType = "String", example = "UUID", required = true)
    private String id;

    @TableField(value = "permissionName")
    @ApiModelProperty(value = "权限名称，只有[零级，一级，二级], 数字越大权限越高，零级为普通用户，一级为管理人员，二级为系统管理者", name = "permissionName", dataType = "String", example = "一级权限")
    private String permissionName;

    @TableField(value = "permissionCode")
    @ApiModelProperty(value = "和permissionName对应，为[0,1,2]，其中1只可以访问数据管理的部分后台，2可以访问所有后台（包括系统界别的监控页面）", name = "permissionCode", dataType = "Integer", example = "1")
    private Integer permissionCode;

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
