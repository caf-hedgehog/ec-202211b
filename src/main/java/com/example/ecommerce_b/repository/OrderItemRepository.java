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

import com.example.ecommerce_b.domain.OrderItem;

/**
 * Order_itemsテーブルを操作するレポジトリ.
 * 
 * @author 萩田
 * 
 *         オーダー関連処理のレポジトリ.
 * 
 * @author shibatamasayuki
 *
 * 
 */
@Repository
public class OrderItemRepository {

	private static final RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(OrderItem.class);

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 
	 * 注文商品DB登録
	 * 
	 * @param orderItem 注文商品
	 */
	public OrderItem insert(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		if (orderItem.getId() == null) {
			String sql = "INSERT INTO order_items(item_id, order_id, quantity, size) VALUES(:itemId, :orderId, :quantity, :size)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String keyColumnNames[] = { "id" };
			template.update(sql, param, keyHolder, keyColumnNames);
			orderItem.setId(keyHolder.getKey().intValue());
		} else {

		}
		return orderItem;
	}

	/*
	 * カート内商品を１件削除する.
	 * 
	 * @param id orderItemのid
	 */
	public void deleteById(Integer id) {
		String sql = "DELETE FROM order_items WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

	/**
	 * 注文商品を取得.
	 * 
	 * @param orderId 注文ID
	 * @return 注文商品情報
	 */
	public List<OrderItem> findByorderId(Integer orderId) {
		String sql = "SELECT id, item_id, order_id, quantity, size FROM order_items WHERE order_id = :orderId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
		List<OrderItem> orderItemList = template.query(sql, param, ORDER_ITEM_ROW_MAPPER);
		return orderItemList;
	}

}
