package com.tamguo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tamguo.model.ChapterEntity;
import com.tamguo.service.IChapterService;
import com.tamguo.service.ICourseService;


@Controller
public class ChapterController {
	
	@Autowired
	private IChapterService iChapterService;
	@Autowired
	private ICourseService iCourseService;
	
	
	@RequestMapping(value = {"/chapter/{subjectId}/{courseId}.html"}, method = RequestMethod.GET)
    public ModelAndView indexAction(@PathVariable String subjectId , @PathVariable String courseId, ModelAndView model) {
    	model.setViewName("chapter");
    	model.addObject("chapterList", iChapterService.findCourseChapter(courseId));
    	model.addObject("courseList", iCourseService.findBySubjectId(subjectId));
    	model.addObject("subjectId", subjectId);
    	model.addObject("courseId", courseId);
        return model;
    }

	@RequestMapping(value = {"/chapter/findChapter/{courseId}.html"}, method = RequestMethod.GET)
	@ResponseBody
	public List<ChapterEntity> findChapterByCourseId(@PathVariable String courseId){
		return iChapterService.findCourseChapter(courseId);
	}
}
