package com.resillence.category.feign;

import com.resillence.category.dto.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name ="category-service" ,url = "http://localhost:9191/orders")
public interface CategoryFeignClient {

    @GetMapping("")
    List<Order> getOrders();
}
