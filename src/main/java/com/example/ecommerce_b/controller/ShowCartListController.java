package com.example.ecommerce_b.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@Autowired
	private HttpSession session;

	@GetMapping("")
	public String index(Model model) {
		if (session.getAttribute("userId") != null) {
			Integer userId = (Integer) session.getAttribute("userId");
			model.addAttribute("cartList", showCartListService.showCartList(userId, 0));
			return "cart_list";
		}
		return "redirect:/login";
	}

}
