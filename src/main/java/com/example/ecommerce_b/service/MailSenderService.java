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

	public void send(String type) {
		// トークンの生成
		String token = repository.findById((Integer) session.getAttribute("userId")).getPassword();
		// メールアドレス検索
		String email = repository.findById((Integer) session.getAttribute("userId")).getEmail();

		String subject = "";
		String key = "";
		String value = "";
		String path = "";
		if (type.equals("PASSWORD")) {
			subject = "パスワード変更";
			key = "url";
			value = "http://localhost:8080/ec-202211b/insertPage?token=" + token;
			path = "/mailtemplate/sample.txt";
		}
		if (type.equals("FINISH")) {
			subject = "注文完了";
			key = "message";
			value = "注文が完了しました。";
			path = "/mailtemplate/finished.txt";
		}

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);// 決め打ちになっているので登録したメールアドレスを格納する
		message.setFrom("sample@example.com");
		message.setSubject(subject);

		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setTemplateMode(TemplateMode.TEXT);
		templateResolver.setCharacterEncoding("UTF-8");

		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver);

		Map<String, Object> variables = new HashMap<>();
		variables.put("name", session.getAttribute("userName"));
		variables.put(key, value);

		Context context = new Context();
		context.setVariables(variables);

		String text = engine.process(path, context);
		message.setText(text);

		this.mailSender.send(message);
	}

}
