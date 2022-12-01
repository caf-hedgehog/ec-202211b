package com.example.ecommerce_b.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Orderの更新情報を格納するフォーム.
 * 
 * @author shibatamasayuki
 *
 */
public class OrderConfirmationForm {
	/** オーダーのid */
	private Integer orderId;
	/** status */
	private Integer status;
	/** オーダー日 */
	private Date orderDate;
	/** 注文者名 */
	@NotBlank(message="名前を入力して下さい")
	private String destinationName;
	/** 注文者のアドレス */
	@NotBlank(message="メールアドレスを入力して下さい")
	@Email(message="メールアドレスの形式が不正です")
	private String destinationEmail;
	/** 注文者の郵便番号 */
	@NotBlank(message="郵便番号を入力して下さい")
	private String destinationZipcode;
	/** 注文者の住所 */
	@NotBlank(message="住所を入力して下さい")
	private String destinationAddress;
	/** 注文者の電話番号 */
	@NotBlank(message="電話番号を入力して下さい")
	private String destinationTel;
	/** 配達日時(型はとりあえずDate入れてる) */
	private Date deliveryTime;
	/** 支払い方法 */
	private Integer paymentMethod;
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public String getDestinationEmail() {
		return destinationEmail;
	}
	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}
	public String getDestinationZipcode() {
		return destinationZipcode;
	}
	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public String getDestinationTel() {
		return destinationTel;
	}
	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "OrderConfirmationForm [orderId=" + orderId + ", status=" + status + ", orderDate=" + orderDate
				+ ", destinationName=" + destinationName + ", destinationEmail=" + destinationEmail
				+ ", destinationZipcode=" + destinationZipcode + ", destinationAddress=" + destinationAddress
				+ ", destinationTel=" + destinationTel + ", deliveryTime=" + deliveryTime + ", paymentMethod="
				+ paymentMethod + "]";
	}

	
}
