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
public class UpdateUserService {

	@Autowired
	private UserRepository repository;

	/**
	 * ユーザー情報変更.
	 * 
	 * @param user ユーザー情報
	 */
	public void update(User user) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		String hashedPassword = bcpe.encode(user.getPassword());
		user.setPassword(hashedPassword);
		repository.update(user);
	}

	/**
	 * ユーザー情報検索.
	 * 
	 * @param id ユーザーID
	 * @return ユーザー情報
	 */
	public User load(Integer id) {
		User user = repository.findById(id);
		return user;
	}

}
