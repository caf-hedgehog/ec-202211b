package com.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.User;

/**
 * userテーブルを操作するレポジトリ.
 * 
 * @author 萩田
 *
 */
@Repository
public class UserRepository {

	private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ユーザー情報を検索する.
	 * 
	 * @param email メールアドレス
	 * @return ユーザー情報
	 */
	public User findByUser(String email) {
		String sql = "SELECT id, name, email, password, zipcode, address, telephone FROM users WHERE email = :email";

		MapSqlParameterSource param = new MapSqlParameterSource().addValue("email", email);

		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);

		// ユーザーが見つからなかった場合NULLを返す
		if (userList.size() == 0) {
			return null;
		}

		return userList.get(0);
	}

	/**
	 * ユーザー情報格納.
	 * 
	 * @param user ユーザー情報
	 */
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "INSERT INTO users(name, email, password, zipcode, address, telephone) VALUES(:name, :email, :password, :zipcode, :address, :telephone)";
		template.update(sql, param);
	}

	/**
	 * ユーザー情報の変更
	 * 
	 * @param user ユーザー情報
	 */
	public void update(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "UPDATE users SET name=:name, email=:email, password=:password, zipcode=:zipcode, address=:address, telephone=:telephone WHERE id=:id";
		template.update(sql, param);
	}

	/**
	 * ユーザー情報検索.
	 * 
	 * @param id ユーザーID
	 * @return ユーザー情報
	 */
	public User findById(Integer id) {
		String sql = "SELECT id, name, email, password, zipcode, address, telephone FROM users WHERE id = :id";

		MapSqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);

		// ユーザーが見つからなかった場合NULLを返す
		if (userList.size() == 0) {
			return null;
		}

		return userList.get(0);
	}

	/**
	 * パスワード検索.
	 * 
	 * @param hash ハッシュ化されたパスワード
	 * @return ユーザー情報
	 */
	public User findByHash(String hash) {
		String sql = "SELECT id, name, email, password, zipcode, address, telephone FROM users WHERE password = :password";

		MapSqlParameterSource param = new MapSqlParameterSource().addValue("password", hash);

		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);

		// ユーザーが見つからなかった場合NULLを返す
		if (userList.size() == 0) {
			return null;
		}

		return userList.get(0);
	}

}
