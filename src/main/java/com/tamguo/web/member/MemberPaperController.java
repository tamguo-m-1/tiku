package com.tamguo.web.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tamguo.model.MemberEntity;
import com.tamguo.service.IPaperService;
import com.tamguo.util.ExceptionSupport;
import com.tamguo.util.Result;

@Controller
public class MemberPaperController {
	
	@Autowired
	private IPaperService iPaperService;
	

	@RequestMapping(value = "/member/paperList", method = RequestMethod.GET)
	public ModelAndView paperList(ModelAndView model, HttpSession session){
		model.setViewName("member/paperList");
		MemberEntity member = ((MemberEntity)session.getAttribute("currMember"));
		model.addObject("paperList", iPaperService.findByCreaterId(member.getUid()));
		return model;
	}
	
	@RequestMapping("member/paperList/updatePaperName.html")
	@ResponseBody
	public Result updatePaperName(String paperId , String name){
		try {
			iPaperService.updatePaperName(paperId , name);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("修改试卷名称", this.getClass(), e);
		}
	}
	
}
