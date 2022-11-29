package com.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.Order;

/**
 * ordersテーブルを操作するレポジトリ.
 * 
 * @author 萩田
 *
 */
@Repository
public class OrderRepository {

	private static final RowMapper<Order> ORDER_ROW_MAPPER = new BeanPropertyRowMapper<>(Order.class);

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 注文の登録.
	 * 
	 * @param order 注文
	 */
	public Order insert(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		if (order.getId() == null) {
			String sql = "INSERT INTO orders(user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method) VALUES(:userId, :status, :totalPrice, :orderDate, :destinationName, :destinationEmail, :destinationZipcode, :destinationAddress, :destinationTel, :deliveryTime, :paymentMethod)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String keyColumnNames[] = { "id" };
			template.update(sql, param, keyHolder, keyColumnNames);
			order.setId(keyHolder.getKey().intValue());
		} else {
		}
		return order;
	}

	/**
	 * カートの中身を表示.
	 * 
	 * @param status ステータス
	 * @param userId ユーザーID
	 * @return
	 */
	public Order findByStatusAndUserId(Integer status, Integer userId) {
		String sql = "SELECT id, user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method FROM orders WHERE user_id=:userId AND status=:status ORDER BY id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		List<Order> order = template.query(sql, param, ORDER_ROW_MAPPER);
		return order.get(0);
	}

}
