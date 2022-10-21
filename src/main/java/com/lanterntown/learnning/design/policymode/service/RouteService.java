package com.lanterntown.learnning.design.policymode.service;

import com.lanterntown.learnning.design.policymode.bo.PayRequest;
import com.lanterntown.learnning.design.policymode.bo.PayResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Service
public class RouteService {

    @Autowired
    Set<PayService> payServiceSet;

    Map<String, PayService> payServiceMap;

    public PayResult epay(PayRequest payRequest) {
        PayService payService = payServiceMap.get(payRequest.getChannelNo());
        return  payService.epay(payRequest );
    }

    /**
     * 首先通过自动注入 PayService 一个集合，然后我们再将其转为一个 Map ，
     * 这样内部存储刚好是唯一标识与实现类的映射了。
     */
    @PostConstruct //@PostConstruct: spring容器初始化的时候执行该方法。
    public void init() {
        payServiceMap = new HashMap<>();
        for (PayService payService : payServiceSet) {
            payServiceMap.put(payService.channel(), payService);
        }
    }
}
