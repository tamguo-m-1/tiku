package com.tamguo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tamguo.model.CourseEntity;
import com.tamguo.service.IAreaService;
import com.tamguo.service.IChapterService;
import com.tamguo.service.ICourseService;

/**
 * Controller - 考试（高考，建造师，医药师）
 * 
 * @author candy.tam
 *
 */
@Controller
public class SubjectController {
	
	@Autowired
	private ICourseService iCourseService;
	@Autowired
	private IChapterService iChapterService;
	@Autowired
	private IAreaService iAreaService;

	@RequestMapping(value = {"/subject/{subjectId}.html"}, method = RequestMethod.GET)
    public ModelAndView indexAction(@PathVariable String subjectId , ModelAndView model) {
    	model.setViewName("subject");
    	model.addObject("subjectId", subjectId);
    	List<CourseEntity> courseList = null;
    	CourseEntity course = null;
    	courseList = iCourseService.findBySubjectId(subjectId);
    	if(courseList != null && !courseList.isEmpty()){
    		course = courseList.get(0);
    	}
    	model.addObject("courseList", courseList);
    	model.addObject("chapterList" , course != null ? iChapterService.findCourseChapter(course.getUid()) : null);
    	model.addObject("course" , course);
    	model.addObject("areaList", iAreaService.findAll());
        return model;
    }
	
}
