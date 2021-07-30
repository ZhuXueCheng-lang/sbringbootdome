package com.gtzn.digitcard.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户信息表zyb_dc_user(ZybDcUser)实体类
 *
 * @author makejava
 * @since 2021-02-22 10:52:23
 */
@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "用户信息表", description = "用户信息表")
@TableName("zyb_dc_user")
public class ZybDcUser implements Serializable {
//    private static final long serialVersionUID = -27612648166374697L;

    @Tolerate
    public ZybDcUser(){};

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = " 主键", example = "")
    private Integer id;
    /**
     * 手机号
     */
    @ApiModelProperty(name = "mobilePhone", value = " 手机号", example = "")
    @NotNull(message = "手机号不能为空")
    private String mobilePhone;
    /**
     * 姓名
     */
    @ApiModelProperty(name = "userName", value = " 姓名", example = "")
    @NotNull(message = "姓名不能为空")
    private String userName;
    /**
     * 密码（未使用预留）
     */
    @ApiModelProperty(name = "password", value = " 密码（未使用预留）", example = "")
    private String password;
    /**
     * 工作单位ID
     */
    @ApiModelProperty(name = "companyId", value = " 工作单位ID", example = "")
    private Integer companyId;
    /**
     * 工作单位名称
     */
    @ApiModelProperty(name = "companyName", value = " 工作单位名称", example = "")
    @NotNull(message = "工作单位名称不能为空")
    private String companyName;
    /**
     * 科室
     */
    @ApiModelProperty(name = "department", value = " 科室", example = "")
    private String department;
    /**
     * 工号（未使用预留）
     */
    @ApiModelProperty(name = "jobNumber", value = " 工号（未使用预留）", example = "")
    private String jobNumber;
    /**
     * 在职状态：1在职，2离职
     */
    @ApiModelProperty(name = "jobStatus", value = " 在职状态：1在职，2离职", example = "")
    private Integer jobStatus;
    /**
     * 支付密码
     */
    @ApiModelProperty(name = "payPassword", value = " 支付密码", example = "")
    private String payPassword;
    /**
     * 账户余额
     */
    @ApiModelProperty(name = "accountBalance", value = " 账户余额", example = "")
    private Double accountBalance;
    /**
     * 注册时间
     */
    @ApiModelProperty(name = "registerTime", value = " 注册时间", example = "")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "注册时间不能为空")
    private LocalDateTime registerTime;
    /**
     * 启/停用状态:1启用，2停用
     */
    @ApiModelProperty(name = "enableStatus", value = " 启/停用状态:1启用，2停用", example = "")
    private Integer enableStatus;
    /**
     * 权限等级：1超级管理员，2管理员，3员工
     */
    @ApiModelProperty(name = "accessLevel", value = " 权限等级：1超级管理员，2管理员，3员工", example = "")
    private Integer accessLevel;
    /**
     * 是否已设置支付密码：1未设置，2已设置
     */
    @ApiModelProperty(name = "isPayPassword", value = " 是否已设置支付密码：1未设置，2已设置", example = "")
    private Integer isPayPassword;


    /**
     * 验证码
     */
    @ApiModelProperty(name = "code", value = " 验证码",example = "")
    @TableField(exist = false)
    private String code;

}