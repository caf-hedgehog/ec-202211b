package com.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.repository.OrderItemRepository;

/**
 * order_itemテーブルを操作するサービス.
 * 
 * @author shibatamasayuki
 *
 */
@Transactional
@Service
public class DeleteCartService {

	@Autowired
	OrderItemRepository repository;
	
	/**
	 * 削除処理を行う.
	 * 
	 * @param orderItemId orderItemのid
	 */
	public void deleteCart(Integer orderItemId) {
		repository.deleteById(orderItemId);
	}
}
