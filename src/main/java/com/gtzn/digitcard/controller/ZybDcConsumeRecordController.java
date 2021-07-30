package com.gtzn.digitcard.controller;

import com.gtzn.digitcard.service.ZybDcUserService;
import com.gtzn.digitcard.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 消费记录表zyb_dc_consume_record(ZybDcConsumeRecord)表控制层
 *
 * @author makejava
 * @since 2021-02-22 11:25:35
 */
@RestController
@Log4j2
@RequestMapping("zybDcConsumeRecord")
@Api(value = "消费记录接口",tags = {"消费记录接口"})
@Validated
public class ZybDcConsumeRecordController {
    /**
     * 服务对象
     */
    @Resource
    private ZybDcUserService zybDcUserService;

    @PostMapping("test")
    @ApiOperation(value = "测试接口",notes = "测试接口")
    public R refund() {
        return R.success("123");
    }


}
