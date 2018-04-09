package com.tamguo.web.admin;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.tamguo.model.QuestionEntity;
import com.tamguo.service.IQuestionService;
import com.tamguo.util.ExceptionSupport;
import com.tamguo.util.Result;

@Controller(value="adminQuestionController")
public class TikuQuestionController {

	@Autowired
	private IQuestionService iQuestionService;
	
	@RequestMapping("admin/question/list")
	@RequiresPermissions("tiku:question:list")
	@ResponseBody
	public Map<String, Object> list(String name , Integer page , Integer limit){
		Page<QuestionEntity> list = iQuestionService.list(name, page, limit);
		return Result.jqGridResult(list.getResult(), list.getTotal(), limit, page, list.getPages());
	}
	
	@RequestMapping("admin/question/info/{questionId}.html")
	@RequiresPermissions("tiku:question:list")
	@ResponseBody
	public Result info(@PathVariable String questionId){
		try {
			return Result.result(0, iQuestionService.select(questionId), null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("查询题目", this.getClass(), e);
		}
	}
	
	@RequestMapping("admin/question/delete")
	@ResponseBody
	public Result delete(@RequestBody String[] questionIds){
		try {
			iQuestionService.deleteBatch(questionIds);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("删除题目", this.getClass(), e);
		}
	}
	
}
