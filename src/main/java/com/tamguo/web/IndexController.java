package com.tamguo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexAction(ModelAndView model) {
    	model.setViewName("index");
        return model;
    }
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView mainAction(ModelAndView model) {
    	model.setViewName("index");
        return model;
    }
    
}
