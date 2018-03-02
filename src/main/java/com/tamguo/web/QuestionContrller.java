package com.tamguo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.tamguo.model.ChapterEntity;
import com.tamguo.model.QuestionEntity;
import com.tamguo.service.IChapterService;
import com.tamguo.service.ICourseService;
import com.tamguo.service.IQuestionService;
import com.tamguo.util.Result;

@Controller
public class QuestionContrller {

	@Autowired
	private IQuestionService iQuestionService;
	@Autowired
	private IChapterService iChapterService;
	@Autowired
	private ICourseService iCourseService;
	
	@RequestMapping(value = {"/question/{subjectId}/{courseId}/{parentChapterId}/{chapterId}-{offset}-{limit}.html"}, method = RequestMethod.GET)
	public ModelAndView questionList(@PathVariable String subjectId , @PathVariable String courseId , 
			@PathVariable String parentChapterId ,@PathVariable String chapterId , @PathVariable Integer offset , 
			@PathVariable Integer limit , ModelAndView model){
		model.setViewName("questionList");
		ChapterEntity chapter = iChapterService.findById(chapterId);
		ChapterEntity parentChapter = iChapterService.findById(parentChapterId);
		ChapterEntity nextChapter = iChapterService.findNextPoint(chapter.getParentId() , chapter.getOrders());
		Page<QuestionEntity> questionList = iQuestionService.findByChapterId(chapterId , offset , limit);
		model.addObject("chapter", chapter);
		model.addObject("parentChapter" , parentChapter);
		model.addObject("nextChapter" , nextChapter);
		model.addObject("questionList", questionList);
		model.addObject("subjectId", subjectId);
		model.addObject("courseId", courseId);
		return model;
	}
	
	@RequestMapping(value = {"/question/{uid}.html"}, method = RequestMethod.GET)
	public ModelAndView question(@PathVariable String uid , ModelAndView model){
		model.setViewName("question");
		model.addObject("question", iQuestionService.findById(uid));
		return model;
	}
	
	@RequestMapping(value = {"/question/getQuestion/{uid}.html"}, method = RequestMethod.GET)
	@ResponseBody
	public Result getQuestion(@PathVariable String uid , ModelAndView model){
		return Result.successResult(iQuestionService.findById(uid));
	}
	
}
