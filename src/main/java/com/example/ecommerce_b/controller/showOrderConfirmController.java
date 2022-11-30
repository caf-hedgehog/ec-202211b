package com.example.ecommerce_b.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order-confirm")
public class showOrderConfirmController {

	@GetMapping("")
	public String index() {
		return "order_confirm";
	}
	
	@PostMapping("/order")
	public String order(Integer orderId, Model model) {
		System.out.println("orderId取得結果" + orderId);
//		Order order = service.findByOrderId(orderId);
//		System.out.println("order内容：　" + order);
		
		return "redirect:/order-confirm";
	}
}
