package com.pinke.liudao.pinke_order.services;

import com.pinke.liudao.pinke_order.entities.OrderResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccessTokenService {

    public boolean doCheckToken(String token, OrderResponseEntity orderResponseEntity){
        RestTemplate restTemplate = new RestTemplate();
        String host = "127.0.0.1";
        String port = "10002";
        OrderResponseEntity temp = restTemplate.getForObject(
                "http://" + host + ":" + port + "/account/check_token?token={token}",
                OrderResponseEntity.class,
                token
        );
        if(temp != null){
            if(temp.getRetCode() == 10000){
                return true;
            }else{
                orderResponseEntity.setRetCode(temp.getRetCode());
                orderResponseEntity.setRetMsg(temp.getRetMsg());
                return false;
            }
        }
        return false;
    }
}
