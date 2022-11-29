package com.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.Topping;

/**
 * toppingsテーブルを操作するリポジトリ.
 * 
 * @author yamaokahayato,萩田
 *
 */
@Repository
public class ToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = new BeanPropertyRowMapper<>(Topping.class);

	/**
	 * トッピング情報を全件検索する.
	 * 
	 * @return トッピング一覧
	 */
	public List<Topping> findAll() {
		String sql = "SELECT id, name, price_m, price_l FROM toppings";
		List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);
		return toppingList;
	}

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
