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
import com.example.ecommerce_b.domain.Topping;
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
		List<Order> orderList = orderRepository.findByStatusAndUserId(status, userId);
		if (orderList == null) {
			return null;
		}
		Order order = orderList.get(0);
		Integer totalSum = 0;

		// 注文商品リストにオーダーIDと一致する商品を取得
		List<OrderItem> orderItemList = orderItemRepository.findByOrderId(order.getId());
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
				Topping topping = toppinRepository.load(orderToppingList.get(j).getToppingId());
				orderToppingList.get(j).setTopping(topping);

				// トッピング金額加算
				if (orderItemList.get(i).getSize() == 'M') {
					System.out.println(i);
					totalSum += topping.getPriceM();
				} else if (orderItemList.get(i).getSize() == 'L') {
					totalSum += topping.getPriceL();
				}
			}
			orderItemList.get(i).setOrderToppingList(orderToppingList);
			itemList.add(itemRepository.load(orderItemList.get(i).getItemId()));
			orderItemList.get(i).setItem(itemList.get(i));

			// トッピング金額加算
			if (orderItemList.get(i).getSize() == 'M') {
				totalSum += itemList.get(i).getPriceM();
			} else if (orderItemList.get(i).getSize() == 'L') {
				totalSum += itemList.get(i).getPriceL();
			}
		}
		order.setTotalPrice(totalSum);
		order.setOrderItemList(orderItemList);
		return order;
	}

}
