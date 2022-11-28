package com.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("")
	public String index(ShowItemListForm form) {
		return "item_list_curry";
	}
	
	@PostMapping("/showlist")
	public String showList(ShowItemListForm form, Model model) {
		List<Item> itemName = showItemListService.serchByName(form.getName());
		model.addAttribute("itemName", itemName);
		return "redirect:/showItemList";
	}

}
