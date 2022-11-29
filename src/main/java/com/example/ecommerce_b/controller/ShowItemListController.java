package com.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.form.ShowItemListForm;
import com.example.ecommerce_b.service.ShowItemListService;

/**
 *商品情報を操作するコントローラー. 
 * 
 * @author yamaokahayato
 *
 */
@Controller
@RequestMapping("/showItemList")
public class ShowItemListController {
	
	@Autowired
	private ShowItemListService showItemListService;
	
	
	/**
	 * 検索欄からの曖昧検索.
	 * 
	 * @param form
	 * @param model
	 * @return　商品一覧画面
	 */
	@GetMapping("/showList")
	public String showList(ShowItemListForm form, Model model) {
		List<Item> itemList = showItemListService.searchByName(form.getName());
		model.addAttribute("itemList", itemList);
		return "item_list_curry";
	}

}