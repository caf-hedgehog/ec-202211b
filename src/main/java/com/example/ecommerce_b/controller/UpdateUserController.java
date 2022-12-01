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
		if (updateUserService.update(user)) {
			model.addAttribute("user", updateUserService.load((Integer) session.getAttribute("userId")));
			return "redirect:/complete";
		}
		model.addAttribute("updateError", "パスワードが違います");
		return userPage(model);
	}

	/**
	 * @return パスワード変更ページ
	 */
	@GetMapping("/update-password")
	public String updatePass(Model model) {
		return "update_password";
	}

	/**
	 * パスワード変更処理.
	 * 
	 * @param password    現在のパスワード
	 * @param newPassword 新しいパスワード
	 * @param confirm     確認用パスワード
	 * @param model       リクエストパラメータ
	 * @return 完了画面
	 */
	@PostMapping("/pass")
	public String pass(String password, String newPassword, String confirm, Model model) {
		if (!newPassword.equals(confirm)) {
			model.addAttribute("updateError", "パスワードが違います");
			return updatePass(model);
		}
		updateUserService.updatePassword(password, newPassword);
		return "redirect:/complete";

	}

	/**
	 * 完了ページ遷移.
	 * 
	 * @return 完了ページ
	 */
	@GetMapping("/complete")
	public String done() {
		return "update_finished";
	}

}
