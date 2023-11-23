package cn.itcast.feign.clients;

import cn.itcast.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("userservice")
//@Component
public interface UserClient {
    //定义方法
    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);
}