package com.example.ecommerce_b.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.ecommerce_b.domain.OrderTopping;

/**
 * order_toppingsテーブルを操作するレポジトリ.
 * 
 * @author 萩田
 *
 */
public class OrderToppingRepository {

	private static final RowMapper<OrderTopping> ORDER_TOPPING_ROW_MAPPER = new BeanPropertyRowMapper<>(
			OrderTopping.class);

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * トッピングをDBに登録.
	 * 
	 * @param orderTopping 注文トッピング
	 */
	public void insert(OrderTopping orderTopping) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderTopping);
		String sql = "INSERT INTO order_toppings(topping_id, order_item_id) VALUES(:toppingId, :orderItemId)";
		template.update(sql, param);
	}

}
