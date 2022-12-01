package com.example.ecommerce_b.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.User;
import com.example.ecommerce_b.form.UserForm;
import com.example.ecommerce_b.service.UpdateUserService;

/**
 * ユーザー情報を変更する.
 * 
 * @author 萩田
 *
 */
@Controller
@RequestMapping("")
public class UpdateUserController {

	@Autowired
	private UpdateUserService updateUserService;

	@Autowired
	private HttpSession session;

	/**
	 * ユーザー情報変更ページ遷移.
	 * 
	 * @return ユーザー情報変更画面
	 */
	@GetMapping("/user-page")
	public String userPage(Model model) {
		model.addAttribute("user", updateUserService.load((Integer) session.getAttribute("userId")));
		return "user-page";
	}

	/**
	 * ユーザー情報変更.
	 * 
	 * @param form  ユーザーフォーム
	 * @param model リクエストパラメータ
	 * @return ユーザー情報変更ページ
	 */
	@PostMapping("/update-user")
	public String update(UserForm form, Model model) {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		updateUserService.update(user);
		model.addAttribute("complete", "変更が完了しました。");
		model.addAttribute("user", updateUserService.load((Integer) session.getAttribute("userId")));
		return "redirect:/user-page";
	}

}
