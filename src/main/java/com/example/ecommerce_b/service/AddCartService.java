package com.example.ecommerce_b.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.domain.OrderItem;
import com.example.ecommerce_b.domain.OrderTopping;
import com.example.ecommerce_b.domain.Topping;
import com.example.ecommerce_b.form.AddCartForm;
import com.example.ecommerce_b.repository.OrderItemRepository;
import com.example.ecommerce_b.repository.OrderRepository;
import com.example.ecommerce_b.repository.OrderToppingRepository;
import com.example.ecommerce_b.repository.ToppingRepository;

/**
 * 
 * 
 * @author 萩田
 *
 */
@Transactional
@Service
public class AddCartService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	@Autowired
	private ToppingRepository toppingRepository;

	public void AddOrder(AddCartForm form, Integer userId) {
		List<OrderTopping> orderToppings = new ArrayList<>();
		List<OrderItem> orderItems = new ArrayList<>();
		Order order = new Order();
		for (int i = 0; i < form.getToppingIdList().size(); i++) {
			orderToppings.add(new OrderTopping(null, form.getToppingIdList().get(i), null,
					toppingRepository.load(form.getToppingIdList().get(i))));
		}
		orderItems.add(
				new OrderItem(null, null, null, form.getArea(), form.getResponsibleCompany(), null, orderToppings));
		order.setOrderItemList(orderItems);
		System.out.println(order);
	}

}
