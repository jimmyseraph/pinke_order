package com.pinke.liudao.pinke_order.actions;

import com.pinke.liudao.pinke_order.entities.OrderDTO;
import com.pinke.liudao.pinke_order.entities.OrderEntity;
import com.pinke.liudao.pinke_order.entities.OrderFilter;
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

    @RequestMapping(value="/add", method = RequestMethod.POST)
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

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public OrderResponseEntity searchOrder(
            @RequestHeader("Access-Token") String token,
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestParam(value = "isPaid", required = false) String isPaid,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "amountLow", required = false) String amountLow,
            @RequestParam(value = "amountHigh", required = false) String amountHigh,
            @RequestParam(value = "receiver", required = false) String receiver,
            @RequestParam(value = "status", required = false) String status
    ){
        OrderResponseEntity orderResponseEntity = new OrderResponseEntity();
        // 1、检查access token
        if(!accessTokenService.doCheckToken(token, orderResponseEntity)){
            return orderResponseEntity;
        }
        OrderFilter orderFilter = new OrderFilter();
        // 2、检查OrderId，如果orderId有值，则将其转换为数字型，并传递给orderFilter
        if(orderId != null){
            try {
                orderFilter.setOrderId(Integer.parseInt(orderId));
            }catch(Exception e){
                e.printStackTrace();
                orderResponseEntity.setRetCode(30005);
                orderResponseEntity.setRetMsg("order id is NAN");
                return orderResponseEntity;
            }
        }
        // 3、检查isPaid，如果isPaid有值，则将其转为数字型，并传递给orderFilter
        if(isPaid != null){
            try {
                orderFilter.setIsPaid(Integer.parseInt(isPaid));
            }catch(Exception e){
                e.printStackTrace();
                orderResponseEntity.setRetCode(30006);
                orderResponseEntity.setRetMsg("isPaid is NAN");
                return orderResponseEntity;
            }
        }
        // 4、检查address，如果address有值，则将其传递给orderFilter
        if(address != null){
            orderFilter.setAddress(address);
        }
        // 5、检查amountLow，如果amountLow有值，则将其转为double型，并传递给orderFilter
        if(amountLow != null){
            try {
                orderFilter.setAmountLow(Double.parseDouble(amountLow));
            }catch(Exception e){
                e.printStackTrace();
                orderResponseEntity.setRetCode(30007);
                orderResponseEntity.setRetMsg("amountLow is NAN");
                return orderResponseEntity;
            }
        }
        // 6、检查amountHigh，如果amountHigh有值，则将其转为double型，并传递给orderFilter
        if(amountHigh != null){
            try {
                orderFilter.setAmountHigh(Double.parseDouble(amountHigh));
            }catch(Exception e){
                e.printStackTrace();
                orderResponseEntity.setRetCode(30008);
                orderResponseEntity.setRetMsg("amountHigh is NAN");
                return orderResponseEntity;
            }
        }
        // 7、检查receiver，如果receiver有值，则将其传递给orderFilter
        if(receiver != null){
            orderFilter.setReceiver(receiver);
        }
        // 8、检查status，如果status有值，则将其转为int型，并传递给orderFilter
        if(status != null){
            try {
                orderFilter.setStatus(Integer.parseInt(status));
            }catch(Exception e){
                e.printStackTrace();
                orderResponseEntity.setRetCode(30009);
                orderResponseEntity.setRetMsg("status is NAN");
                return orderResponseEntity;
            }
        }
        // 深入到sevice层，完成查询操作
        orderService.doSearchOrder(orderFilter, orderResponseEntity);
        return orderResponseEntity;
    }
}
