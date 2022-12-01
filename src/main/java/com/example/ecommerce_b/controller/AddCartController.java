package com.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@Autowired
	private ShowCartListController showCartListController;

	@GetMapping("")
	public String index() {
		return "item_detail";
	}

	@PostMapping("/cart-add")
	public String addCart(AddCartForm form, Integer userId, Model model) {
		addCartService.AddOrder(form, userId);
		return showCartListController.index(model, userId);
	}

}
