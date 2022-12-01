package com.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.form.OrderConfirmationForm;
import com.example.ecommerce_b.service.OrderConfirmationService;

/**
 * orderテーブルを操作するコントローラー.(注文実行を行う)
 * 
 * @author shibatamasayuki
 *
 */
@Controller
@RequestMapping("/order")
public class OrderConfirmationController {

	@Autowired
	OrderConfirmationService service;

	@Autowired
	ShowOrderConfirmController controller;

	/**
	 * 注文完了画面に遷移.
	 * 
	 * @param form 注文内容に関するform
	 * @return 注文完了画面
	 */
	@GetMapping("")
	public String index(OrderConfirmationForm form) {
		return "order_finished";
	}

	/**
	 * 注文するボタンを押した後注文内容を受け取りorderを更新、その後注文完了画面に遷移.
	 * 
	 * @param form   注文内容に関するform
	 * @param result BindingResult
	 * @param model  model
	 * @return 注文完了画面(redirect)
	 */
	@PostMapping("/confirmation")
	public String orderConfirmation(@Validated OrderConfirmationForm form, BindingResult result, Model model,
			String responsibleCompany) {
		if (result.hasErrors()) {
			return controller.order(model, form);
		}
		service.updateOrder(form);
		return "redirect:/order";
	}

}
