package com.tamguo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.tamguo.model.ChapterEntity;
import com.tamguo.model.CourseEntity;
import com.tamguo.model.SubjectEntity;
import com.tamguo.service.IAreaService;
import com.tamguo.service.IChapterService;
import com.tamguo.service.ICourseService;
import com.tamguo.service.ISubjectService;
import com.tamguo.util.Result;

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
	@Autowired
	private ISubjectService iSubjectService;

	@RequestMapping(value = {"subject/{subjectId}.html"}, method = RequestMethod.GET)
    public ModelAndView indexAction(@PathVariable String subjectId , ModelAndView model) {
		SubjectEntity subject = iSubjectService.find(subjectId);
		CourseEntity course = iCourseService.find(subject.getCourseId());
		List<ChapterEntity> chapterList = iChapterService.findCourseChapter(subject.getCourseId());
    	model.setViewName("subject");
    	model.addObject("subject", subject);
    	model.addObject("course" , course);
    	model.addObject("courseList", subject.getCourseList());
    	model.addObject("chapterList" , chapterList);
    	model.addObject("areaList", iAreaService.findAll());
        return model;
    }
	
	@RequestMapping(value = {"subject/getCourseTree.html"}, method = RequestMethod.GET)
	@ResponseBody
	public Result getCourseTree(){
		JSONArray list = iSubjectService.getCourseTree();
		return Result.successResult(list);
	}
}
