package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/order/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        System.out.println("第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/feign/order/get/{id}")
    public ResultData getOrderInfo(@PathVariable("id") Integer id) {
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
        ResultData payInfo = null;
        try {
            System.out.println("----调用开始:" + DateUtil.now());
            payInfo = payFeignApi.getPayInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("----调用开始:" + DateUtil.now());
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return payInfo;
    }

    @GetMapping("/feign/order/myLb")
    public String getMyLb() {
        return payFeignApi.getMyLb();
    }
}
