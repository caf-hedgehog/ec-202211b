package com.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

		String sql = "SELECT id, name, mail_address, password, zip_code, address, telephone FROM users WHERE mail_address = :email";

		MapSqlParameterSource param = new MapSqlParameterSource().addValue("email", email);

		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);

		// ユーザーが見つからなかった場合NULLを返す
		if (userList.size() == 0) {
			return null;
		}

		return userList.get(0);
	}

}
