package com.pinke.liudao.pinke_order.daos;

import com.pinke.liudao.pinke_order.entities.OrderDTO;
import com.pinke.liudao.pinke_order.entities.OrderEntity;
import com.pinke.liudao.pinke_order.entities.OrderFilter;

import java.util.List;

public interface OrderDAO {
    int insertOrder(OrderDTO order);
    int enableOrderById(int orderId);
    int disableOrderById(int orderId);
    int modifyOrder(OrderDTO orderDTO);
    List<OrderDTO> getOrders(OrderFilter filter);
    OrderDTO getOrderById(int orderId);
}
