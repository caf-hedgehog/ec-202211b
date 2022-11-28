package com.example.ecommerce_b.form;

import java.util.List;

/**
 * 商品カート追加フォーム.
 * 
 * @author 萩田
 *
 */
public class AddCartForm {
	/** 商品ID */
	private Integer itemId;
	/** サイズ */
	private Character size;
	/** 数量 */
	private Integer quantity;
	/** トッピングリスト */
	private List<Integer> toppingIdList;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Integer> getToppingIdList() {
		return toppingIdList;
	}

	public void setToppingIdList(List<Integer> toppingIdList) {
		this.toppingIdList = toppingIdList;
	}

	@Override
	public String toString() {
		return "AddCartForm [itemId=" + itemId + ", size=" + size + ", quantity=" + quantity + ", toppingIdList="
				+ toppingIdList + "]";
	}

}
