package com.example.ecommerce_b.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.domain.OrderItem;
import com.example.ecommerce_b.domain.OrderTopping;
import com.example.ecommerce_b.repository.ItemRepository;
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
	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 注文表を取得.
	 * 
	 * @param userId ユーザーID
	 * @param status ステータスID
	 * @return
	 */
	public Order showCartList(Integer userId, Integer status) {
		// ユーザーIDとステータスから注文前の情報を取得
		Order order = orderRepository.findByStatusAndUserId(status, userId).get(0);

		// 注文商品リストにオーダーIDと一致する商品を取得
		List<OrderItem> orderItemList = orderItemRepository.findByOrderId(order.getId());
//		if (orderItemList.isEmpty()) {
//			return order = null;
//		}
		List<Item> itemList = new ArrayList<>();

		for (int i = 0; i < orderItemList.size(); i++) {
			// トッピングリストを取得
			List<OrderTopping> orderToppingList = orderToppingRepository
					.findByOrderItemId(orderItemList.get(i).getId());

			for (int j = 0; j < orderToppingList.size(); j++) {
				if (orderToppingList.get(j).getToppingId() == 0) {
					orderToppingList.get(j).setTopping(null);
					break;
				}
				orderToppingList.get(j).setTopping(toppinRepository.load(orderToppingList.get(j).getToppingId()));
			}
			orderItemList.get(i).setOrderToppingList(orderToppingList);
			itemList.add(itemRepository.load(orderItemList.get(i).getItemId()));
			orderItemList.get(i).setItem(itemList.get(i));
		}

		order.setOrderItemList(orderItemList);
		return order;
	}

}
