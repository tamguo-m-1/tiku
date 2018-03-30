package com.tamguo.web.admin;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.tamguo.model.PaperEntity;
import com.tamguo.service.IPaperService;
import com.tamguo.util.Result;

@Controller(value="adminPaperController")
public class TikuPaperController {
	
	@Autowired
	private IPaperService iPaperService;

	@RequestMapping("admin/paper/list")
	@RequiresPermissions("tiku:paper:list")
	@ResponseBody
	public Map<String, Object> list(String name , Integer page , Integer limit){
		Page<PaperEntity> list = iPaperService.list(name, page, limit);
		return Result.jqGridResult(list.getResult(), list.getTotal(), limit, page, list.getPages());
	}
	
	
}
