package com.lanterntown.learnning.design.policyMode.service.impl;

import com.lanterntown.learnning.design.policyMode.bo.PayRequest;
import com.lanterntown.learnning.design.policyMode.bo.PayResult;
import com.lanterntown.learnning.design.policyMode.service.PayService;
import org.springframework.stereotype.Service;

@Service("weChatPayService")
public class WeChatPayService implements PayService {


    @Override
    public PayResult epay(PayRequest request) {
        // 业务逻辑
        return new PayResult("weChat");
    }
    @Override
    public String channel() {
        return "002";
    }
}