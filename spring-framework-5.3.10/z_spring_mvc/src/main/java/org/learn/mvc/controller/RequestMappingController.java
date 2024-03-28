package org.learn.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/request")
public class RequestMappingController {
	@RequestMapping("/mapping")
	public ModelAndView mapping() {
		System.out.println(this.getClass().getName() + " working");
		ModelAndView modelAndView = new ModelAndView("a");
		modelAndView.addObject("source", "RequestMappingController");
		return modelAndView;
	}
}
