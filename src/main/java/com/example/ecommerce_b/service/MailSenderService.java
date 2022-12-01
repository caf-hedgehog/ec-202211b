package com.example.ecommerce_b.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.example.ecommerce_b.repository.UserRepository;

/**
 * メールを送信するサービス.
 * 
 * @author 萩田
 *
 */
@Service
public class MailSenderService {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private HttpSession session;

	@Autowired
	private UserRepository repository;

	public void send() {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("kensyou.happy.tarou@gmail.com");
		message.setFrom("sample@example.com");
		message.setSubject("Test");

		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setTemplateMode(TemplateMode.TEXT);
		templateResolver.setCharacterEncoding("UTF-8");

		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver);

		String token = repository.findById((Integer) session.getAttribute("userId")).getPassword();

		Map<String, Object> variables = new HashMap<>();
		variables.put("name", session.getAttribute("userName"));
		variables.put("url", "http://localhost:8080/ec-202211b/insertPage?token=" + token);

		Context context = new Context();
		context.setVariables(variables);

		String text = engine.process("/mailtemplate/sample.txt", context);
		message.setText(text);

		this.mailSender.send(message);
	}

}
