package com.example.ecommerce_b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.domain.OrderItem;
import com.example.ecommerce_b.domain.OrderTopping;
import com.example.ecommerce_b.domain.Topping;
import com.example.ecommerce_b.repository.OrderItemRepository;
import com.example.ecommerce_b.repository.OrderRepository;
import com.example.ecommerce_b.repository.OrderToppingRepository;
import com.example.ecommerce_b.repository.ToppingRepository;

/**
 * ordersテーブルを操作するレポジトリ.
 * 
 * @author 萩田
 *
 */
@Transactional
@Service
public class ShowCartListService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	@Autowired
	private ToppingRepository toppinRepository;

	/**
	 * 注文表を取得.
	 * 
	 * @param userId ユーザーID
	 * @param status ステータスID
	 * @return
	 */
	public Order showCartList(Integer userId, Integer status) {
		Order order = orderRepository.findByStatusAndUserId(status, userId);
		OrderItem orderItem = orderItemRepository.findByorderId(order.getId());
		List<OrderTopping> orderToppingList = orderToppingRepository.findByOrderId(order.getId());

		for (int i = 0; i < orderToppingList.size(); i++) {
			orderToppingList.get(i).setTopping(toppinRepository.load(orderToppingList.get(i).getToppingId()));
		}

		System.out.println("order-->" + order);
		System.out.println("orderItem-->" + orderItem);
		System.out.println("orderTopping-->" + orderToppingList);
		return order;
	}

}
