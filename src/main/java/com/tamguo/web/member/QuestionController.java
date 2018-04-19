package com.tamguo.web.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tamguo.model.QuestionEntity;
import com.tamguo.service.IQuestionService;
import com.tamguo.util.ExceptionSupport;
import com.tamguo.util.Result;

@Controller(value="memberQuestionController")
public class QuestionController {
	
	@Autowired
	private IQuestionService iQuestionService;
	
	@RequestMapping(value = "/member/addQuestion", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model){
		model.setViewName("member/addQuestion");
		return model;
	}
	
	@RequestMapping(value = "/member/submitQuestion", method = RequestMethod.POST)
	@ResponseBody
	public Result submitQuestion(@RequestBody QuestionEntity question){
		try {
			iQuestionService.addQuestion(question);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("添加试题", this.getClass(), e);
		}
	}
	

}
