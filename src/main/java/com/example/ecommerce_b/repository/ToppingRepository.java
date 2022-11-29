package com.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.Topping;

/**
 * toppingsテーブルを操作するレポジトリ.
 * 
 * @author 萩田
 *
 */
@Repository
public class ToppingRepository {

	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = new BeanPropertyRowMapper<>(Topping.class);

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 対象のトッピングを取得.
	 * 
	 * @param id トッピングID
	 * @return トッピング
	 */
	public Topping load(Integer id) {
		String sql = "SELECT id, name, price_m, price_l FROM toppings WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Topping topping = template.queryForObject(sql, param, TOPPING_ROW_MAPPER);
		return topping;
	}

}
