package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUnit;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Tag(name = "支付微服务模块", description = "支付CRUD")
@RestController
@Slf4j
public class PayController {

    @Resource
    PayService payService;

    @Operation(summary = "新增", description = "新增支付流水方法,json串做参数")
    @PostMapping(value = "/pay/add")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return ResultData.success("成功插入记录，返回值：" + i);
    }

    @Operation(summary = "删除", description = "删除支付流水方法")
    @GetMapping(value = "/pay/del/{id}")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        return ResultData.success(payService.delete(id));
    }

    @Operation(summary = "修改", description = "修改支付流水方法")
    @PostMapping(value = "/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return ResultData.success("成功修改记录，返回值：" + i);
    }

    @Operation(summary = "按照ID查流水", description = "查询支付流水方法")
    @GetMapping(value = "/pay/get/{id}")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        if (id == -4) {
            throw new RuntimeException("id不能为负数");
        }
        try {
            TimeUnit.SECONDS.sleep(62);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResultData.success(payService.getById(id));
    }

    @Operation(summary = "查询所有流水", description = "查询支付流水方法")
    @GetMapping(value = "/pay/getAll")
    public ResultData<List<Pay>> getAll() {
        return ResultData.success(payService.getAll());
    }

    @GetMapping(value = "/pay/error")
    public ResultData<Integer> getError() {

        Integer status = Integer.valueOf("200");
        try {
            System.out.println("come in pay error test");
            int test = 10 / 0;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return ResultData.success(status);

    }

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/pay/get/info")
    public String getConsulInfo(@Value("${atguigu.info}") String info) {
        return "atguigu Info: " + info + "\t" + "port: " + port;
    }

}
