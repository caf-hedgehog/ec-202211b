package com.example.ecommerce_b.service;

import java.util.ArrayList;
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
	public List<List<Item>> searchByName(String name) {
		List<Item> itemList = null;
		
		if (name == null) {
			itemList = itemRepository.findAll();
		} else {
			itemList = itemRepository.findByName(name);
		}
		
		List<List<Item>> itemListList = createItemListList(itemList);

		return itemListList;
	}


	
	/**
	 * 商品の並び順を価格の高い順、低い順で並び替える.
	 * 
	 * @param order
	 * @return　商品一覧
	 */
	public List<List<Item>> itemSort(String sort) {
		List<Item> itemList = itemRepository.findAll(sort);
		
		List<List<Item>> itemListList = createItemListList(itemList);
		
		return itemListList;
	}
	
	
	
	private List<List<Item>> createItemListList(List<Item> itemList) {
		List<List<Item>> itemListList = new ArrayList<>();
		List<Item> threeItemList = new ArrayList<>();
		for(int i = 0; i < itemList.size(); i++) {
			if(i % 3 == 0) {
				threeItemList = new ArrayList<>();
				itemListList.add(threeItemList);
			}
			threeItemList.add(itemList.get(i));
		}
		return itemListList;
	}

}
