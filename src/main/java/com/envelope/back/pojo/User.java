package com.envelope.back.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户")
@Accessors(chain = true)
public class User implements Serializable {

    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(value = "唯一标识ID", name = "id", dataType = "String", example = "UUID", required = true)
    private String id;

    @TableField(value = "name")
    @ApiModelProperty(value = "用户真实姓名", name = "name", dataType = "String", example = "王洋")
    private String name;

    @TableField(value = "nickname")
    @ApiModelProperty(value = "用户昵称", name = "nickname", dataType = "String", example = "xiye")
    private String nickname;

    @TableField(value = "password")
    @ApiModelProperty(value = "登录密码, 由MD5加密", name = "password", dataType = "String", example = "6sy2xfd9")
    private String password;

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

    @TableField(value = "phone")
    @ApiModelProperty(value = "手机号", name = "phone", dataType = "Long")
    private Long phone;

    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱", name = "email", dataType = "String")
    @Email
    private String email;

    @TableField(value = "address")
    @ApiModelProperty(value = "地址", name = "address", dataType = "String")
    private String address;

    @TableField(value =  "description")
    @ApiModelProperty(value = "描述", name = "description", dataType = "String")
    private String description;

}
