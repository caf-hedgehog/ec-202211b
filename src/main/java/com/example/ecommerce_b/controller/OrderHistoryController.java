package com.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.service.OrderHistoryService;

@Controller
@RequestMapping("/order-hisotry")
public class OrderHistoryController {

	@Autowired
	private OrderHistoryService orderHistoryService;

	@GetMapping("")
	public String showOrder(Model model, Integer userId) {
		List<Order> orders = orderHistoryService.showOrderHistory(userId, 2);// statusどうしよう。。。
		model.addAttribute("orders", orders);
		return "order-history";
	}

}
