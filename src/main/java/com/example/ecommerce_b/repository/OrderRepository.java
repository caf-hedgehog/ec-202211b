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
 * @author 萩田, shibatamasayuki
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
	public List<Order> findByStatusAndUserId(Integer status, Integer userId) {
		String sql = "SELECT id, user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method FROM orders WHERE user_id=:userId AND status=:status ORDER BY id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		List<Order> orderList = template.query(sql, param, ORDER_ROW_MAPPER);
		if (orderList.size() == 0) {
			return null;
		}

		return orderList;
	}

	/**
	 * 引数で受け取ったidと一致するオーダーを検索して1件取得.
	 *
	 * @param id orderのid
	 * @return idが一致するオーダー
	 */
	public Order load(Integer id) {
		String sql = "SELECT id, user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method FROM orders WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param, ORDER_ROW_MAPPER);
	}

	/**
	 * 注文を行う.
	 * 
	 * @return order order
	 */
	public void update(Order order) {
		String sql = "UPDATE orders SET status = :status, order_date = :orderDate, destination_name = :destinationName, destination_email = :destinationEmail, destination_zipcode  = :destinationZipcode, destination_address  = :destinationAddress, destination_tel  = :destinationTel, delivery_time  = :deliveryTime, payment_method  = :paymentMethod WHERE id = :id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		template.update(sql, param);
	}

	/** coding by shibata */
	public List<Order> findByUserId(Integer userId) {
		String sql = "SELECT id, user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method FROM orders WHERE user_id = :userId AND status >= 1 ORDER BY id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		List<Order> orderList = template.query(sql, param, ORDER_ROW_MAPPER);
		if (orderList.size() == 0) {
			return null;
		}
		return orderList;
	}

	/**
	 * テーブルデータ削除.
	 * 
	 * @param userId ユーザーID
	 * @param hashId ハッシュ化されたセッションID
	 */
	public void delete(Integer userId, Integer hashId) {
		String sql = "DELETE FROM orders WHERE id=:hashId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("hashId", hashId);
		template.update(sql, param);
	}

}
