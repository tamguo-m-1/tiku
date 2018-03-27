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
import com.tamguo.model.SubjectEntity;
import com.tamguo.service.ISubjectService;
import com.tamguo.util.ExceptionSupport;
import com.tamguo.util.Result;

@Controller(value="adminSubjectController")
public class SubjectController {
	
	@Autowired
	private ISubjectService iSubjectService;
	
	@RequestMapping("admin/subject/list")
	@RequiresPermissions("tiku:subject:list")
	@ResponseBody
	public Map<String, Object> list(String name , Integer page , Integer limit){
		Page<SubjectEntity> list = iSubjectService.list(name, page, limit);
		return Result.jqGridResult(list.getResult(), list.getTotal(), limit, page, list.getPages());
	}
	
	@RequestMapping("admin/subject/find/{subjectId}")
	@ResponseBody
	public Result find(@PathVariable String subjectId){
		return Result.result(200, iSubjectService.find(subjectId), "");
	}
	
	@RequestMapping("admin/subject/save")
	@ResponseBody
	public Result save(@RequestBody SubjectEntity subject){
		try {
			iSubjectService.save(subject);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("新增考试", this.getClass(), e);
		}
		return Result.result(0, null, "");
	}
	
	@RequestMapping("admin/subject/update")
	@ResponseBody
	public Result update(@RequestBody SubjectEntity subject){
		try {
			iSubjectService.update(subject);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("更新考试", this.getClass(), e);
		}
		return Result.result(0, null, "");
	}	
	
	@RequestMapping("admin/subject/delete")
	@ResponseBody
	public Result delete(@RequestBody String[] subjectIds){
		try {
			iSubjectService.deleteBatch(subjectIds);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("删除考试", this.getClass(), e);
		}
	}

}
