package com.tamguo.web.admin;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.tamguo.model.MenuEntity;
import com.tamguo.model.SubjectEntity;
import com.tamguo.service.IMenuService;
import com.tamguo.util.Result;

@Controller(value="adminMenuController")
public class TikuMenuController {
	
	@Autowired
	private IMenuService iMenuService;

	@RequestMapping("admin/menu/list")
	@RequiresPermissions("tiku:menu:list")
	@ResponseBody
	public Map<String, Object> list(String name , Integer page , Integer limit){
		Page<SubjectEntity> list = iMenuService.list(name, page, limit);
		return Result.jqGridResult(list.getResult(), list.getTotal(), limit, page, list.getPages());
	}
	
	@RequestMapping("admin/menu/getMenuTree")
	@RequiresPermissions("tiku:menu:list")
	@ResponseBody
	public Result getMenuTree(){
		List<MenuEntity> menus = iMenuService.getMenuTree(); 
		return Result.result(0, menus, null);
	}
}
