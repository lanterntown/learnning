package com.lanterntown.learnning.design.policyMode.service;


import com.lanterntown.learnning.design.policyMode.bo.PayRequest;
import com.lanterntown.learnning.design.policyMode.bo.PayResult;

/**
 * spring巧用策略模式：https://cloud.tencent.com/developer/article/1707691
 * 抽象出一组公共的接口 PayService
 */
public interface PayService {


    /**
     * 将支付抽出一个公共接口
     * @param request
     * @return
     */
    PayResult epay(PayRequest request);

    String channel();
}