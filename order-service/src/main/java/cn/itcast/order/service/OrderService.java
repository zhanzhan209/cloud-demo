package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.利用feign进行远程调用,更加优雅
        User user = userClient.findById(order.getUserId());
        //封装信息
        order.setUser(user);
        // 4.返回
        return order;
    }
/*    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.利用RestTemplate发送请求查询用户信息
        String url ="http://userservice/user/" + order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        //封装信息
        order.setUser(user);
        // 4.返回
        return order;
    }*/
}
