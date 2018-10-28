package com.pinke.liudao.pinke_order.actions;

import com.pinke.liudao.pinke_order.entities.OrderDTO;
import com.pinke.liudao.pinke_order.entities.OrderEntity;
import com.pinke.liudao.pinke_order.entities.OrderResponseEntity;
import com.pinke.liudao.pinke_order.services.AccessTokenService;
import com.pinke.liudao.pinke_order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
public class OrderAction {

    @Autowired
    private AccessTokenService accessTokenService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value="add", method = RequestMethod.POST)
    @ResponseBody
    public OrderResponseEntity addOrder(
            @RequestHeader("Access-Token") String token,
            @RequestBody OrderEntity orderEntity
            ){
        OrderResponseEntity orderResponseEntity = new OrderResponseEntity();
        // 1、检查access token
        if(!accessTokenService.doCheckToken(token, orderResponseEntity)){
            return orderResponseEntity;
        }
        // 2、检查address
        if(orderEntity.getAddress() == null || orderEntity.getAddress().trim().equals("")){
            orderResponseEntity.setRetCode(30001);
            orderResponseEntity.setRetMsg("Address is null");
            return orderResponseEntity;
        }
        // 3、检查amount
        if(orderEntity.getAmount() < 0){
            orderResponseEntity.setRetCode(30002);
            orderResponseEntity.setRetMsg("Amount is less than 0.00");
            return orderResponseEntity;
        }
        // 4、检查收件人receiver
        if(orderEntity.getReceiver() == null || orderEntity.getReceiver().trim().equals("")){
            orderResponseEntity.setRetCode(30003);
            orderResponseEntity.setRetMsg("Receiver is null");
            return orderResponseEntity;
        }

        OrderDTO order = new OrderDTO();
        order.setIsPaid(0);
        order.setAddress(orderEntity.getAddress());
        order.setAmount(orderEntity.getAmount());
        order.setReceiver(orderEntity.getReceiver());
        order.setStatus(1);
        // 调用service执行add order
        orderService.doAddOrder(order, orderResponseEntity);
        return orderResponseEntity;
    }

    @RequestMapping(value = "/{orderId}/enable", method = RequestMethod.PUT)
    public OrderResponseEntity enable(
            @RequestHeader("Access-Token") String token,
            @PathVariable("orderId") int orderId
    ){
        OrderResponseEntity orderResponseEntity = new OrderResponseEntity();
        // 1、检查access token
        if(!accessTokenService.doCheckToken(token, orderResponseEntity)){
            return orderResponseEntity;
        }
        orderService.doChangeStatus(1, orderId, orderResponseEntity);
        return orderResponseEntity;
    }

    @RequestMapping(value = "/{orderId}/disable", method = RequestMethod.PUT)
    public OrderResponseEntity disable(
            @RequestHeader("Access-Token") String token,
            @PathVariable("orderId") int orderId
    ){
        OrderResponseEntity orderResponseEntity = new OrderResponseEntity();
        // 1、检查access token
        if(!accessTokenService.doCheckToken(token, orderResponseEntity)){
            return orderResponseEntity;
        }
        orderService.doChangeStatus(0, orderId, orderResponseEntity);
        return orderResponseEntity;
    }

    @RequestMapping(value="/{orderId}/modify", method = RequestMethod.POST)
    public OrderResponseEntity modify(
            @RequestHeader("Access-Token") String token,
            @PathVariable("orderId") int orderId,
            @RequestBody OrderEntity orderEntity
    ){
        OrderResponseEntity orderResponseEntity = new OrderResponseEntity();
        // 1、检查access token
        if(!accessTokenService.doCheckToken(token, orderResponseEntity)){
            return orderResponseEntity;
        }
        // 2、检查address
        if(orderEntity.getAddress() == null || orderEntity.getAddress().trim().equals("")){
            orderResponseEntity.setRetCode(30001);
            orderResponseEntity.setRetMsg("Address is null");
            return orderResponseEntity;
        }
        // 3、检查amount
        if(orderEntity.getAmount() < 0){
            orderResponseEntity.setRetCode(30002);
            orderResponseEntity.setRetMsg("Amount is less than 0.00");
            return orderResponseEntity;
        }
        // 4、检查收件人receiver
        if(orderEntity.getReceiver() == null || orderEntity.getReceiver().trim().equals("")){
            orderResponseEntity.setRetCode(30003);
            orderResponseEntity.setRetMsg("Receiver is null");
            return orderResponseEntity;
        }
        orderService.doModifyOrder(orderId, orderEntity, orderResponseEntity);
        return orderResponseEntity;
    }
}
