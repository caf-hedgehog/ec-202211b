package com.example.ecommerce_b.service;

import javax.servlet.http.HttpSession;

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

	@Autowired
	private HttpSession session;

	/**
	 * ユーザー情報変更.
	 * 
	 * @param user ユーザー情報
	 */
	public boolean update(User user) {
		User oldUserInfo = load((Integer) session.getAttribute("userId"));
		user.setId((Integer) session.getAttribute("userId"));

		if (!passwordCheck(user.getPassword(), oldUserInfo.getPassword())) {
			return false;
		}

		if (user.getName() == null) {
			user.setName(oldUserInfo.getName());
		}
		if (user.getEmail() == null) {
			user.setEmail(oldUserInfo.getEmail());
		}
		if (user.getZipcode() == null) {
			user.setZipcode(oldUserInfo.getZipcode());
		}
		if (user.getAddress() == null) {
			user.setAddress(oldUserInfo.getAddress());
		}
		if (user.getTelephone() == null) {
			user.setTelephone(oldUserInfo.getTelephone());
		}
		user.setPassword(oldUserInfo.getPassword());
		repository.update(user);

		return true;
	}

	/**
	 * パスワード変更.
	 * 
	 * @param password    旧パスワード
	 * @param newPassword 新しいパスワード
	 * @return
	 */
	public boolean updatePassword(String password, String newPassword) {
		User user = load((Integer) session.getAttribute("userId"));
		System.out.println(user);
		if (passwordCheck(password, user.getPassword())) {
			BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
			String hashedPassword = bcpe.encode(newPassword);
			user.setPassword(hashedPassword);
			repository.update(user);
			System.out.println("ok");
			return true;
		}
		return false;
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

	/**
	 * パスワード確認.
	 * 
	 * @param password    平文
	 * @param confirmPass ハッシュ化されたパスワード
	 * @return
	 */
	public boolean passwordCheck(String password, String confirmPass) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		if (bcpe.matches(password, confirmPass)) {
			return true;
		}
		return false;
	}

}
