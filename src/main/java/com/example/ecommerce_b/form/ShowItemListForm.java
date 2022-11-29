package com.example.ecommerce_b.form;


/**
 * 商品一覧画面の検索欄商品名を受け取るフォーム.
 * 
 * @author yamaokahayato
 *
 */
public class ShowItemListForm {
	
	
	/** 商品名 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ShowItemListForm [name=" + name + "]";
	}
	
	
	

}
