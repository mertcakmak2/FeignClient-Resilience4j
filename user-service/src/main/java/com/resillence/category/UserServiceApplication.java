package com.resillence.category;

import com.resillence.category.dto.Order;
import com.resillence.category.feign.CategoryFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
@EnableFeignClients("com.resillence.category.feign")
public class UserServiceApplication {

    @Autowired
    @Lazy
    private CategoryFeignClient categoryFeignClient;

    public static final String USER_SERVICE="userService";
    private static final String BASEURL = "http://localhost:9191/orders";
    private int attempt=1;

    @GetMapping("/displayOrders")
    @CircuitBreaker(name =USER_SERVICE,fallbackMethod = "getAllAvailableProducts")
    //@Retry(name = USER_SERVICE,fallbackMethod = "getAllAvailableProducts")
    public List<Order> displayOrders() {
        System.out.println("retry method called "+attempt++ +" times "+" at "+new Date());
        List<Order> orders = categoryFeignClient.getOrders();
        return orders;
    }

    public List<Order> getAllAvailableProducts(Exception e){
        List<Order> orders = new ArrayList<>();
        return orders;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
