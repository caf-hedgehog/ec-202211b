package com.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * オーダー関連処理のレポジトリ.
 * 
 * @author shibatamasayuki
 *
 */
@Repository
public class OrderItemRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * カート内商品を１件削除する.
	 * 
	 * @param id orderItemのid
	 */
	public void deleteById(Integer id) {
		String sql = "DELETE FROM order_items WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

}
