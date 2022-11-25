package com.example.ecommerce_b.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.User;
import com.example.ecommerce_b.form.LoginForm;
import com.example.ecommerce_b.service.LoginLogoutService;

/**
 * ユーザー情報を操作するコントローラ.
 * 
 * @author 萩田
 *
 */
@Controller
@RequestMapping("/")
public class LoginLogoutController {

	@Autowired
	private LoginLogoutService service;

	@Autowired
	private HttpSession session;

	/**
	 * ログイン画面表示.
	 * 
	 * @param form リクエストパラメータ
	 * @return ログイン画面
	 */
	@GetMapping("")
	public String index(LoginForm form) {
		return "login";
	}

	/**
	 * 商品一覧ページ表示.
	 * 
	 * @param form リクエストパラメータ
	 * @return 商品一覧ページ
	 */
	@PostMapping("/login")
	public String login(LoginForm form) {
		User user = service.login(form.getEmail(), form.getPassword());
		if (user == null) {
			session.setAttribute("session", user);
			return index(form);
		}
		return "item_detail";
	}
}
