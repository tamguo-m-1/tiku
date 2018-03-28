package com.tamguo.web.admin;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.tamguo.model.MenuEntity;
import com.tamguo.model.SubjectEntity;
import com.tamguo.service.IMenuService;
import com.tamguo.util.ExceptionSupport;
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
	
	@RequestMapping("admin/menu/info/{menuId}.html")
	@RequiresPermissions("tiku:menu:list")
	@ResponseBody
	public Result info(@PathVariable String menuId){
		try {
			return Result.result(0, iMenuService.findById(menuId), null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("查询菜单", this.getClass(), e);
		}
	}
	
	@RequestMapping("admin/menu/save.html")
	@RequiresPermissions("tiku:menu:save")
	@ResponseBody
	public Result save(@RequestBody MenuEntity menu){
		try {
			iMenuService.save(menu);
			return Result.result(0, null, null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("保存菜单", this.getClass(), e);
		}
	}
	
	@RequestMapping("admin/menu/update.html")
	@RequiresPermissions("tiku:menu:update")
	@ResponseBody
	public Result update(@RequestBody MenuEntity menu){
		try {
			iMenuService.update(menu);
			return Result.result(0, null, null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("修改菜单", this.getClass(), e);
		}
	}
	
	@RequestMapping("admin/menu/delete.html")
	@RequiresPermissions("tiku:menu:delete")
	@ResponseBody
	public Result delete(@RequestBody String[] menuIds){
		try {
			iMenuService.deleteBatch(menuIds);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("删除菜单", this.getClass(), e);
		}
	}
}
