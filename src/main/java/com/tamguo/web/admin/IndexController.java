package com.tamguo.web.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value="adminIndexController")
public class IndexController {
	
	private final String INDEX_PAGE = "admin/index";

    @RequestMapping(value = "admin/", method = RequestMethod.GET)
    public ModelAndView indexAction(ModelAndView model) {
    	model.setViewName(INDEX_PAGE);
        return model;
    }
    
    /**
     * 首页
     */
    @RequestMapping(value = "admin/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request,ModelMap model) throws IOException {
        return INDEX_PAGE;
    }
    
   
}