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

	// 试用通配符的话，按精确度排序进行匹配
	// ? > * > {} > **
	@RequestMapping("/mapping*")
	public ModelAndView mapping01() {
		System.out.println(this.getClass().getName() + " working with mapping*");
		ModelAndView modelAndView = new ModelAndView("a");
		modelAndView.addObject("source", "RequestMappingController");
		return modelAndView;
	}

	@RequestMapping("/mapping?")
	public ModelAndView mapping02() {
		System.out.println(this.getClass().getName() + " working with mapping?");
		ModelAndView modelAndView = new ModelAndView("a");
		modelAndView.addObject("source", "RequestMappingController");
		return modelAndView;
	}

	@RequestMapping("/**")
	public ModelAndView mapping03() {
		System.out.println(this.getClass().getName() + " working with /**");
		ModelAndView modelAndView = new ModelAndView("a");
		modelAndView.addObject("source", "RequestMappingController");
		return modelAndView;
	}

	@RequestMapping("/{mapping}")
	public ModelAndView mapping04() {
		System.out.println(this.getClass().getName() + " working with {mapping} ");
		ModelAndView modelAndView = new ModelAndView("a");
		modelAndView.addObject("source", "RequestMappingController");
		return modelAndView;
	}
}
