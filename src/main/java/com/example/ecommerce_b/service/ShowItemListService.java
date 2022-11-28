package com.example.ecommerce_b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.repository.ItemRepository;

/**
 * 商品一覧情報を操作するサービス.
 * 
 * @author yamaokahayato
 *
 */
@Service
@Transactional
public class ShowItemListService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * item情報を全件検索する業務処理と商品名を曖昧検索する業務処理.
	 * 
	 * @param name 商品名
	 * @return　商品リスト
	 */
	public List<Item> searchByName(String name) {
		if (name == null) {
			List<Item> itemList = itemRepository.findAll();
			return itemList;
		}

		List<Item> itemList = itemRepository.findByName(name);
		return itemList;
	}

}
