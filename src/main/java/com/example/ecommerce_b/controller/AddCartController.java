package com.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.form.AddCartForm;
import com.example.ecommerce_b.service.AddCartService;

/**
 * 
 * 
 * @author 萩田
 *
 */
@Controller
@RequestMapping("/cart")
public class AddCartController {

	@Autowired
	private AddCartService addCartService;

	@GetMapping("")
	public String index() {
		return "item_detail";
	}

	@PostMapping("/cart-list")
	public String addCart(AddCartForm form) {
		System.out.println(form);
		addCartService.AddOrder(form, 1/* ユーザーID */);
		return "redirect:/show-cart";
	}

}
