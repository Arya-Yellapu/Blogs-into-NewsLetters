package com.blog.newsLetter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.blog.newsLetter.dao.DaoImpl;

import jakarta.transaction.Transactional;

@Controller
public class HomeController {
	
	@Autowired
	DaoImpl daoImpl;

	@RequestMapping("/")
	public ModelAndView landingPage()
	{
		ModelAndView m1 = new ModelAndView("index");
		return m1;
	}
	
	@RequestMapping("/subscribe")
	@Transactional
	public ModelAndView subscriptionPage(@RequestParam String email,@RequestParam String links)
	{
		daoImpl.insertData(email, links);
		ModelAndView m1 = new ModelAndView("subscribed");
		return m1;
	}
}
