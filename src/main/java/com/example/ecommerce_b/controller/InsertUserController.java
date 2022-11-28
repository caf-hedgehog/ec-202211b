package com.example.ecommerce_b.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.User;
import com.example.ecommerce_b.form.InsertUserForm;
import com.example.ecommerce_b.service.InsertUserService;

/**
 * userテーブルを操作するコントローラ.
 * 
 * @author 萩田
 *
 */
@Controller
@RequestMapping("/insert")
public class InsertUserController {

	@Autowired
	private InsertUserService service;

	/**
	 * 登録画面表示.
	 * 
	 * @return 登録画面
	 */
	@GetMapping("")
	public String index() {
		return "register_user";
	}

	/**
	 * ログイン画面遷移.
	 * 
	 * @param form リクエストパラメータ
	 * @return ログイン画面
	 */
	@PostMapping("/register")
	public String insert(InsertUserForm form) {
		System.out.println(form);
		User user = new User();
		BeanUtils.copyProperties(form, user);
		System.out.println(user);
		service.insert(user);
		return "redirect:/";
	}
}
