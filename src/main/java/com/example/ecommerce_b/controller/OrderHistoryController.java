package com.example.ecommerce_b.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.service.OrderHistoryService;

/**
 * 注文履歴を表示.
 * 
 * @author 萩田
 *
 */
@Controller
@RequestMapping("/order-hisotry")
public class OrderHistoryController {

	@Autowired
	private OrderHistoryService orderHistoryService;

	@Autowired
	private HttpSession session;

	/**
	 * 注文履歴表示.
	 * 
	 * @param model リクエストパラメータ
	 * @return 注文履歴。 ログインしていない場合はログい画面に戻る
	 */
	@GetMapping("")
	public String showOrder(Model model) {
		if (session.getAttribute("userId") != null) {
			List<Order> orders = orderHistoryService.showOrderHistory((Integer) session.getAttribute("userId"), 1);// statusどうしよう。。。
			model.addAttribute("orders", orders);
			return "order-history";
		}
		return "/login";
	}

}
