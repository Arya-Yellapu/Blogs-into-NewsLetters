package com.blog.newsLetter.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.blog.newsLetter.dao.DaoImpl;
import com.blog.newsLetter.dao.MailConnectionEntity;
import com.blog.newsLetter.dao.MailsEntity;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

@RestController
public class APIController {
	
	@Value("${spring.mail.username")
	private String fromAddress;
	
	@Autowired
	DaoImpl daoImpl;
	
	@Autowired
	JavaMailSender sender;

	@GetMapping(value="/sendNewsLetters")
	@Transactional
	public ResponseEntity<String> sendBlogs() throws MessagingException
	{
		ResponseEntity<String> response = null;
		
		List<MailsEntity> list= daoImpl.getMailAndLinks();
		if(list.size()==0)
		{
			response = new ResponseEntity<String>("Map is Empty",HttpStatus.BAD_REQUEST);
		}
		else
		{
            RestTemplate rs = new RestTemplate();
            LocalDate today = LocalDate.now();
            
            for(MailsEntity m : list)
            {
            	MailsEntity me = m;
            	String mailId= me.getMailId();
            	
            	List<MailConnectionEntity> ls = me.getMailConnectionList();
            	ls.stream().forEach(n->{
            		ResponseEntity<String> res = rs.getForEntity(n.getLink(), String.class);
    				String flag=res.getBody();
    				
            		MimeMessage message = sender.createMimeMessage();
    				MimeMessageHelper helper;
					try {
						helper = new MimeMessageHelper(message,true);
						helper.setTo(mailId);
	    				helper.setFrom(fromAddress);
	    				helper.setSubject("Blogs for "+today);
	    				helper.setText(flag, true);
	    				sender.send(message);
					} catch (MessagingException e1) {
						
						e1.printStackTrace();
					}
    			});
            }
            response = new ResponseEntity<String>("Blogs Sent",HttpStatus.OK);

		}
		
		return response;
		
	}
}
