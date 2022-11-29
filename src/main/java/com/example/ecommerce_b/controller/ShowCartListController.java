package com.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.service.ShowCartListService;

/**
 * ordersテーブルを操作するコントローラ.
 * 
 * @author 萩田
 *
 */
@Controller
@RequestMapping("/show-cart")
public class ShowCartListController {

	@Autowired
	private ShowCartListService showCartListService;

	@GetMapping("")
	public String index() {
		showCartListService.showCartList(1, 0);
		return "cart_list";
	}

}
