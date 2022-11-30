package com.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.service.ShowItemListService;

/**
 * 商品情報を操作するコントローラー.
 * 
 * @author yamaokahayato
 *
 */
@Controller
@RequestMapping("/showItemList")
public class ShowItemListController {

	@Autowired
	private ShowItemListService showItemListService;

	@GetMapping("/show")
	public String index(Model model) {
		List<Item> itemList = showItemListService.searchByName(null);
		model.addAttribute("itemList", itemList);
		return "item_list_curry";
	}

	/**
	 * 検索欄からの曖昧検索.
	 * 
	 * @param form
	 * @param model
	 * @return 商品一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(String itemName, Model model) {
		List<Item> itemList = showItemListService.searchByName(itemName);
		model.addAttribute("itemList", itemList);
		return "item_list_curry";
	}

	/**
	 * 商品の並び替え欄から送られた内容で商品の並び替えを表示する
	 * 
	 * @param order
	 * @param model モデル
	 * @return 商品一覧画面
	 */
	@PostMapping("/itemSort")
	public String itemSort(String sort, Model model) {
		List<Item> itemList = showItemListService.itemSort(sort);
		model.addAttribute("itemList", itemList);
		return "item_list_curry";
	}

}
