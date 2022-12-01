package com.example.ecommerce_b.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.form.OrderConfirmationForm;
import com.example.ecommerce_b.repository.OrderRepository;

/**
 * order情報の更新に関連するサービス.
 * 
 * @author shibatamasayuki
 *
 */
@Service
@Transactional
public class OrderConfirmationService {

	@Autowired
	OrderRepository repository;

	/**
	 * 注文処理を行う.
	 * 
	 * @return order
	 */
	public void updateOrder(OrderConfirmationForm form) {
		String deliveryTime = String.format("%s %s:00:00", form.getDeliveryTime(), form.getResponsibleCompany());
		SimpleDateFormat dateParser = new SimpleDateFormat("MM-dd-yy HH:mm:ss");
		try {
			Date date = dateParser.parse(deliveryTime);
			Timestamp ts = new Timestamp(date.getTime());
			Order order = new Order();
			order.setId(form.getOrderId());
			order.setStatus(form.getPaymentMethod());
			order.setDestinationName(form.getDestinationName());
			order.setDestinationEmail(form.getDestinationEmail());
			order.setDestinationZipcode(form.getDestinationZipcode());
			order.setDestinationAddress(form.getDestinationAddress());
			order.setDestinationTel(form.getDestinationTel());
			order.setPaymentMethod(form.getPaymentMethod());
			order.setDeliveryTime(ts);
			repository.update(order);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
