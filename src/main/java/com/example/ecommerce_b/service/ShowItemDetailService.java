package com.example.ecommerce_b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.domain.Topping;
import com.example.ecommerce_b.repository.ItemRepository;
import com.example.ecommerce_b.repository.ToppingRepository;

/**
 * 商品詳細情報を操作するサービス.
 * 
 * @author yamaokahayato
 *
 */
@Service
@Transactional
public class ShowItemDetailService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	public Item detailByItem(Integer id) {
		Item item = itemRepository.load(id);
		List<Topping> toppingList = toppingRepository.findAll();
		item.setToppingList(toppingList);
		return item;
	}

}
