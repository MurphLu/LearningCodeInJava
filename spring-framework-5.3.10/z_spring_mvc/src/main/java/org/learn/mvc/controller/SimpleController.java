package org.learn.mvc.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SimpleController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(this.getClass().getName() + " working");
		ModelAndView modelAndView = new ModelAndView("a");
		modelAndView.addObject("source", "SimpleController");
		return modelAndView;
	}
}
