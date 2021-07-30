package com.gtzn.digitcard.model.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;

/**
 * (PayPasswordVO)支付密码封装类
 *
 * @author makejava
 * @since 2021-02-24 11:33:52
 */
@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "支付密码封装类",description = "支付密码封装类")
public class PayPasswordVO {

    @Tolerate
    public PayPasswordVO(){};

    /**
     * 支付密码
     */
    @ApiModelProperty(name = "payPassword", value = " 支付密码",example = "")
    @TableField(exist = false)
    @NotNull(message = "支付密码不能为空")
    @NumberFormat(pattern="######")
    private String payPassword;

}
