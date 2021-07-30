package com.gtzn.digitcard.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.gtzn.digitcard.constant.CommonConstants;
import com.gtzn.digitcard.exception.ApiException;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author
 */
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String Code = CommonConstants.SUCCESS;

    @Getter
    @Setter
    private String msg = "success";


    @Getter
    @Setter
    private T data;

    public static <T> R<T> success(T data) {
        RBuilder<T> builder = R.builder();
        builder.data(data);
        builder.Code(CommonConstants.SUCCESS);
        builder.msg("success");
        return builder.build();
    }

    public static <T> R<T> ok(T data) {
        return success(data);
    }

    public static <T> R<T> fail(String msg) {
        RBuilder<T> builder = R.builder();
        builder.Code(CommonConstants.FAIL);
        builder.msg(msg);
        return builder.build();
    }

    public static <T> R<T> fail(String code, String msg) {
        RBuilder<T> builder = R.builder();
        builder.Code(code);
        builder.msg(msg);
        return builder.build();
    }

    public static <T> R<T> fail(String code, String msg, T data) {
        RBuilder<T> builder = R.builder();
        builder.Code(code);
        builder.msg(msg);
        builder.data(data);
        return builder.build();
    }

    public R() {
        super();
    }


    public R(T data) {
        super();
        this.data = data;
    }


    public R(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public R(T data, String msg, int total, int page) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public R(Throwable e) {
        super();
        this.msg = "服务器异常";
        this.Code = CommonConstants.FAIL;
    }

    public static R error(String errorCode, String msg) {
        return fail(errorCode,msg);
    }

    public static R error(String msg) {
        return fail(msg);
    }

    public R error(String errorCode, String msg, T data) {
        return R.builder()
                .Code(errorCode)
                .msg(msg)
                .data(data)
                .build();
    }

    public R error(ApiException e) {
        return R.builder()
                .Code(e.getErrorCode())
                .msg(e.getMessage())
                .data(e.getData())
                .build();
    }


}
