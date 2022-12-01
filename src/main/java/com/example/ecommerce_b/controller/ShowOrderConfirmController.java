package com.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.domain.OrderItem;
import com.example.ecommerce_b.domain.OrderTopping;
import com.example.ecommerce_b.domain.Topping;
import com.example.ecommerce_b.form.OrderConfirmationForm;
import com.example.ecommerce_b.service.ShowOrderConfirmService;

/**
 * 注文確認画面遷移を行う.
 * 
 * @author shibatamasayuki
 *
 */
@Controller
@RequestMapping("/order-confirm")
public class ShowOrderConfirmController {

	@Autowired
	ShowOrderConfirmService showOrderConfirmService;

	/**
	 * 注文確認画面に遷移.
	 * 
	 * @param orderId orderのid
	 * @param model   model
	 * @return 注文確認画面
	 */
	@PostMapping("/order")
	public String order(Model model, OrderConfirmationForm form) {
		System.out.println("form内容" + form);
		Order order = showOrderConfirmService.findByOrderId(form.getOrderId());
		List<OrderItem> orderItemList = showOrderConfirmService.findOrderItemListByOrderId(form.getOrderId());
		for (OrderItem orderItem : orderItemList) {
			Item item = showOrderConfirmService.findItemByItemId(orderItem.getItemId());
			orderItem.setItem(item);

			List<OrderTopping> orderToppingList = showOrderConfirmService
					.findOrderToppingListByOrderItemId(orderItem.getId());

			for (OrderTopping orderTopping : orderToppingList) {
				Topping topping = showOrderConfirmService.findToppingByToppingId(orderTopping.getToppingId());
				orderTopping.setTopping(topping);
			}
			orderItem.setOrderToppingList(orderToppingList);
		}

		order.setOrderItemList(orderItemList);
		model.addAttribute("order", order);

		return "order_confirm";
	}
}
