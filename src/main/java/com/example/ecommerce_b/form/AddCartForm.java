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
	private Character responsibleCompany;
	/** 数量 */
	private Integer area;
	/** トッピングリスト */
	private List<Integer> toppingIdList;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Character getResponsibleCompany() {
		return responsibleCompany;
	}

	public void setResponsibleCompany(Character responsibleCompany) {
		this.responsibleCompany = responsibleCompany;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public List<Integer> getToppingIdList() {
		return toppingIdList;
	}

	public void setToppingIdList(List<Integer> toppingIdList) {
		this.toppingIdList = toppingIdList;
	}

	@Override
	public String toString() {
		return "AddCartForm [itemId=" + itemId + ", responsibleCompany=" + responsibleCompany + ", area=" + area
				+ ", toppingIdList=" + toppingIdList + "]";
	}

}
