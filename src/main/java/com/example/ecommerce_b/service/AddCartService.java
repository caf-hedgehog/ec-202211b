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
		// List<OrderTopping> orderToppings = new ArrayList<>();
		// List<OrderItem> orderItems = new ArrayList<>();

		Item item = itemRepository.load(1);// form.getItemId()
		Order order = new Order();
		order.setUserId(userId);
		order.setTotalPrice(0);
		order.setStatus(0);
		order = orderRepository.insert(order);

//		for (int i = 0; i < form.getToppingIdList().size(); i++) {
//			orderToppings.add(new OrderTopping(null, form.getToppingIdList().get(i), order.getId(),
//					toppingRepository.load(form.getToppingIdList().get(i))));
//		}
//		orderItems.add(new OrderItem(null, 1/* form.getItemId() */, order.getId(), form.getArea(),
//				form.getResponsibleCompany(), item, orderToppings));

		// order.setOrderItemList(orderItems);
		OrderItem orderItem = new OrderItem(null, 1/* form.getItemId() */, order.getId(), form.getArea(),
				form.getResponsibleCompany(), item, null);
		orderItemRepository.insert(orderItem);

		OrderTopping orderTopping = new OrderTopping();
		for (int i = 0; i < form.getToppingIdList().size(); i++) {
			orderTopping = new OrderTopping(null, form.getToppingIdList().get(i), order.getId(),
					toppingRepository.load(form.getToppingIdList().get(i)));
			orderToppingRepository.insert(orderTopping);// 回す
		}
	}

}
