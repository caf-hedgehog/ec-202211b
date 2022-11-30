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
 * orderテーブルを操作するサービス.
 *
 * @author shibatamasayuki
 *
 */
@Service
@Transactional
public class ShowOrderConfirmService {
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	OrderToppingRepository orderToppingRepository;
	
	@Autowired
	ToppingRepository toppingRepository;

	/**
	 * オーダーを1件取得.
	 *
	 * @param orderId orderのid
	 * @return オーダー情報
	 */
	public Order findByOrderId(Integer orderId) {
		return orderRepository.load(orderId);
	}
	
	/**
	 * OrderItemのリストを取得.
	 * 
	 * @param orderId
	 * @return orderItemのリスト.
	 */
	public List<OrderItem> findOrderItemListByOrderId(Integer orderId) {
		return orderItemRepository.findByOrderId(orderId);
	}
	
	/**
	 * item情報を取得.
	 * 
	 * @param id id
	 * @return item情報
	 */
	public Item findItemByItemId(Integer id) {
		return itemRepository.load(id);
	}
	
	/**
	 * OrderToppingのリストを取得.
	 * 
	 * @param orderItemId orderItemのid
	 * @return OrderToppingのリスト
	 */
	public List<OrderTopping> findOrderToppingListByOrderItemId(Integer orderItemId) {
		return orderToppingRepository.findByOrderItemId(orderItemId);
	}
	
	/**
	 * topping情報を取得.
	 * 
	 * @param id id
	 * @return topping情報
	 */
	public Topping findToppingByToppingId(Integer id) {
		return toppingRepository.load(id);
	}
	
	
}
