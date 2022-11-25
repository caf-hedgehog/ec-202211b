package com.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.User;
import com.example.ecommerce_b.repository.UserRepository;

/**
 * usersテーブルを操作するサービス.
 * 
 * @author 萩田
 *
 */
@Transactional
@Service
public class LoginLogoutService {

	@Autowired
	private UserRepository repository;

	/**
	 * ハッシュ化されたパスワードをもとにログイン判定を行う.
	 * 
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @return ユーザー情報
	 */
	public User login(String email, String password) {
		User user = repository.findByUser(email);
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		if (bcpe.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}

}
