package com.lanterntown.learnning.design.policymode.service.impl;

import com.lanterntown.learnning.design.policymode.bo.PayRequest;
import com.lanterntown.learnning.design.policymode.bo.PayResult;
import com.lanterntown.learnning.design.policymode.service.PayService;
import org.springframework.stereotype.Service;

@Service("aliPayService")
public class AliPayService implements PayService {


    @Override
    public PayResult epay(PayRequest request) {
        // 业务逻辑
        return new PayResult("ali");
    }
    @Override
    public String channel() {
        return "001";
    }
}
