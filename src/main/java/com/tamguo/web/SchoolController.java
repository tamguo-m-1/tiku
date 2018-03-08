package com.tamguo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tamguo.model.SchoolEntity;
import com.tamguo.service.ISchoolService;
/**
 * Controller - 学校
 * 
 * @author candy.tam
 *
 */
@Controller
public class SchoolController {

	@Autowired
	private ISchoolService iSchoolService;
	
	@RequestMapping(value = {"/school/area/{areaId}.html"}, method = RequestMethod.GET)
	@ResponseBody
	public List<SchoolEntity> findSchoolByAreaId(@PathVariable String areaId , ModelAndView model){
		return iSchoolService.findEliteSchoolPaper(areaId);
	}
	
}
