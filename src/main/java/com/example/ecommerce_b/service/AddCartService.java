package com.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.domain.OrderItem;
import com.example.ecommerce_b.domain.OrderTopping;
import com.example.ecommerce_b.form.AddCartForm;
import com.example.ecommerce_b.repository.ItemRepository;
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
	@Autowired
	private ItemRepository itemRepository;

	/**
	 * カートに追加する.
	 * 
	 * @param form   リクエストフォーム
	 * @param userId ユーザーID
	 */
	public void AddOrder(AddCartForm form, Integer userId) {
		Item item = itemRepository.load(form.getItemId());// form.getItemId()
		Order order = new Order();
		order.setUserId(userId);
		order.setTotalPrice(0);
		order.setStatus(0);
		// 初めて登録するか判定
		if (orderRepository.findByStatusAndUserId(0, userId) == null) {
			order = orderRepository.insert(order);
		}
		OrderItem orderItem = new OrderItem(null, item.getId(),
				orderRepository.findByStatusAndUserId(0, userId).get(0).getId(), form.getArea(),
				form.getResponsibleCompany(), item, null);
		orderItemRepository.insert(orderItem);

		OrderTopping orderTopping = new OrderTopping();
		if (form.getToppingIdList() != null) {
			for (int i = 0; i < form.getToppingIdList().size(); i++) {
				orderTopping = new OrderTopping(null, form.getToppingIdList().get(i), orderItem.getId(),
						toppingRepository.load(form.getToppingIdList().get(i)));
				orderToppingRepository.insert(orderTopping);// 回す
			}
		} 
	}

}
