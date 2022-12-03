package com.example.ecommerce_b.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Order;
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

	/**
	 * カートリスト表示.
	 * 
	 * @param model リクエストパラメータ
	 * @return カートリストページ
	 */
	@GetMapping("")
	public String index(Model model) {
		if (session.getAttribute("userId") != null) {
			Integer userId = (Integer) session.getAttribute("userId");
			Order order = showCartListService.showCartList(userId, 0);
			Order hashOrder = showCartListService.showCartList(Math.abs(session.getId().hashCode()), 0);

			if (hashOrder != null && order != null) {
				for (int i = 0; i < hashOrder.getOrderItemList().size(); i++) {
					order.getOrderItemList().add(hashOrder.getOrderItemList().get(i));
				}
				System.out.println(hashOrder);
				System.out.println(order);
				Integer tempTotalPrice = order.getTotalPrice() + hashOrder.getTotalPrice();
				order.setTotalPrice(tempTotalPrice);
			}

			if (order != null) {
				Integer tax = order.getTax();
				model.addAttribute("tax", tax);
				order.setTotalPrice(tax + order.getTotalPrice());
			}
			model.addAttribute("cartList", order);
			return "cart_list";
		}
		Integer userId = Math.abs(session.getId().hashCode());
		Order order = showCartListService.showCartList(userId, 0);
		if (order != null) {
			Integer tax = order.getTax();
			model.addAttribute("tax", tax);
			order.setTotalPrice(tax + order.getTotalPrice());
		}
		model.addAttribute("cartList", order);
		return "cart_list";
	}
}
