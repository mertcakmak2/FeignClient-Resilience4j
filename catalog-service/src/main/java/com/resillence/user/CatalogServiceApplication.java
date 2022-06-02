package com.resillence.user;

import com.resillence.user.entity.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@RequestMapping("/orders")
public class CatalogServiceApplication {

	@GetMapping
	public List<Order> getOrders(){
		return Stream.of(
				new Order("mobile", "electronics", "white", 20000),
				new Order("T-Shirt", "clothes", "black", 999),
				new Order("Jeans", "clothes", "blue", 1999),
				new Order("Laptop", "electronics", "gray", 50000),
				new Order("digital watch", "electronics", "black", 2500),
				new Order("Fan", "electronics", "black", 50000)
		).collect(Collectors.toList());
	}

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }

}
