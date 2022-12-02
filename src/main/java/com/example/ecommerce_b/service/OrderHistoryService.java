package com.example.ecommerce_b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.domain.OrderItem;
import com.example.ecommerce_b.domain.OrderTopping;
import com.example.ecommerce_b.domain.Topping;
import com.example.ecommerce_b.repository.ItemRepository;
import com.example.ecommerce_b.repository.OrderItemRepository;
import com.example.ecommerce_b.repository.OrderRepository;
import com.example.ecommerce_b.repository.OrderToppingRepository;
import com.example.ecommerce_b.repository.ToppingRepository;

/**
 * ordersテーブルを操作するサービス.
 * 
 * @author 萩田
 *
 */
@Transactional
@Service
public class OrderHistoryService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	@Autowired
	private ToppingRepository toppinRepository;
	@Autowired
	private ItemRepository itemRepository;

//	public List<Order> showOrderHistory(Integer userId, Integer status) {

//		// ユーザーIDとステータスから注文前の情報を取得
//		List<Order> orders = orderRepository.findByStatusAndUserId(status, userId);
//		if (orders == null) {
//			return orders;
//		}
//		// 注文商品リストにオーダーIDと一致する商品を取得
//		List<OrderItem> orderItemList = new ArrayList<>();
//		for (int i = 0; i < orders.size(); i++) {
//			orderItemList.add(orderItemRepository.findByOrderId(orders.get(i).getId()).get(0));
//		}
//
//		for (int i = 0; i < orderItemList.size(); i++) {
//			// トッピングリストを取得
//			List<OrderTopping> orderToppingList = orderToppingRepository
//					.findByOrderItemId(orderItemList.get(i).getId());
//
//			for (int j = 0; j < orderToppingList.size(); j++) {
//				orderToppingList.get(j).setTopping(toppinRepository.load(orderToppingList.get(j).getToppingId()));
//			}
//			orderItemList.get(i).setOrderToppingList(orderToppingList);
//
//			List<Item> itemList = new ArrayList<>();
//			itemList.add(itemRepository.load(orderItemList.get(i).getItemId()));
//
//			orderItemList.get(i).setItem(itemList.get(0));
//
//		}
//		for (int i = 0; i < orders.size(); i++) {
//			List<OrderItem> tmp = new ArrayList<>();
//			tmp.add(orderItemList.get(i));
//			orders.get(i).setOrderItemList(tmp);
//		}
//		return orders;
//	}
	public List<Order> showOrderHistory(Integer userId) {
		// ユーザーIDとステータスから注文前の情報を取得
		List<Order> orders = orderRepository.findByUserId(userId);
		if (orders == null) {
			return orders;
		}

		// 各オーダに情報をぶちこむ
		for (Order order : orders) {
			List<OrderItem> orderItemList = orderItemRepository.findByOrderId(order.getId());
			for (OrderItem orderItem : orderItemList) {
				Item item = itemRepository.load(orderItem.getItemId());
				orderItem.setItem(item);

				List<OrderTopping> orderToppingList = orderToppingRepository.findByOrderItemId(orderItem.getId());
				for (OrderTopping orderTopping : orderToppingList) {
					Topping topping = toppinRepository.load(orderTopping.getToppingId());
					orderTopping.setTopping(topping);
				}
				orderItem.setOrderToppingList(orderToppingList);

			}
			order.setOrderItemList(orderItemList);

		}
		return orders;
	}
}
