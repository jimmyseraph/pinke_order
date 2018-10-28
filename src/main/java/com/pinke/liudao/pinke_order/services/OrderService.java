package com.pinke.liudao.pinke_order.services;

import com.pinke.liudao.pinke_order.daos.OrderDAO;
import com.pinke.liudao.pinke_order.entities.OrderDTO;
import com.pinke.liudao.pinke_order.entities.OrderEntity;
import com.pinke.liudao.pinke_order.entities.OrderResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;

    public  void doAddOrder(OrderDTO order, OrderResponseEntity orderResponseEntity){
        orderDAO.insertOrder(order);
        orderResponseEntity.setRetCode(10000);
        orderResponseEntity.setRetMsg("Order successfully added");
    }

    public void doChangeStatus(int status,int orderId, OrderResponseEntity orderResponseEntity){
        int tmp = 0;
        if(status == 0) {
            tmp = orderDAO.disableOrderById(orderId);
            orderResponseEntity.setRetMsg("order "+orderId+" is disabled");
        }else{
            tmp = orderDAO.enableOrderById(orderId);
            orderResponseEntity.setRetMsg("order "+orderId+" is enabled");
        }
        if(tmp == 0){
            orderResponseEntity.setRetCode(30002);
            orderResponseEntity.setRetMsg("orderId is invalid");
            return;
        }
        orderResponseEntity.setRetCode(10000);
    }

    public void doModifyOrder(int orderId, OrderEntity orderEntity, OrderResponseEntity orderResponseEntity){
        OrderDTO orderDTO = orderDAO.getOrderById(orderId);
        if(orderDTO == null){
            orderResponseEntity.setRetCode(30002);
            orderResponseEntity.setRetMsg("orderId is invalid");
            return;
        }
        orderDTO.setAddress(orderEntity.getAddress());
        orderDTO.setAmount(orderEntity.getAmount());
        orderDTO.setReceiver(orderEntity.getReceiver());
        orderDAO.modifyOrder(orderDTO);
        orderResponseEntity.setRetCode(10000);
        orderResponseEntity.setRetMsg("order " + orderId + " is changed");
    }
}
