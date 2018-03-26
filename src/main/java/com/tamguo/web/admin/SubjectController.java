package com.tamguo.web.admin;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.tamguo.model.SubjectEntity;
import com.tamguo.service.ISubjectService;
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

}
