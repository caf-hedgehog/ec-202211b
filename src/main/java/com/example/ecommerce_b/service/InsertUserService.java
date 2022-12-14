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
public class InsertUserService {

	@Autowired
	private UserRepository repository;

	/**
	 * ユーザー登録.
	 * 
	 * @param user ユーザー情報
	 */
	public void insert(User user) {
		// パスワードのハッシュ化
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		String hashedPassword = bcpe.encode(user.getPassword());
		user.setPassword(hashedPassword);
		repository.insert(user);
	}

}
