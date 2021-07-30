package com.gtzn.digitcard.model.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

/**
 * (UserLoginVO)用户登录封装类
 *
 * @author makejava
 * @since 2021-02-22 16:37:52
 */
@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "用户登录封装类",description = "用户登录封装类")
public class UserLoginVO {

    @Tolerate
    public UserLoginVO(){};

    /**
     * 用户手机号
     */
    @ApiModelProperty(name = "mobilePhone", value = " 用户手机号",example = "")
    @TableField(exist = false)
    private String mobilePhone;
    /**
     * 验证码
     */
    @ApiModelProperty(name = "code", value = " 验证码",example = "")
    @TableField(exist = false)
    private String code;

}
