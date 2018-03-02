package com.tamguo.web.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.tamguo.util.CException;
import com.tamguo.util.ExceptionSupport;
import com.tamguo.util.Result;
import com.tamguo.util.ShiroUtils;
import com.tamguo.model.SysMenuEntity;
import com.tamguo.service.ISysMenuService;

/**
 * 菜单
 */
@Controller
@RequestMapping("admin/sysMenu")
public class SysMenuController {

	@Autowired
	private ISysMenuService sysMenuService;


	/**
	 * 所有菜单列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public @ResponseBody Map<String, Object> list(Integer page, Integer limit) {
		page = page == null ? 0 : page;
		limit = limit == null ? 10 : limit;
		// 查询列表数据
		Page<SysMenuEntity> menuList = sysMenuService.queryList(new HashMap<>(), page, limit);
		
		List<SysMenuEntity> result = menuList.getResult();
		return Result.jqGridResult(result, menuList.getTotal(), limit, page, menuList.getPages());
	}

	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public @ResponseBody Result select() {
		// 查询列表数据
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();

		// 添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		return Result.successResult(menuList);
	}

	/**
	 * 角色授权菜单
	 */
	@RequestMapping("/perms")
	@RequiresPermissions("sys:menu:perms")
	public @ResponseBody Result perms() {
		// 查询列表数据
		List<SysMenuEntity> menuList = sysMenuService.queryList(new HashMap<String, Object>(), 1, 10000);

		return Result.successResult(menuList);
	}

	/**
	 * 菜单信息
	 */
	@RequestMapping("/info/{menuId}")
	@RequiresPermissions("sys:menu:info")
	public @ResponseBody Result info(@PathVariable("menuId") Long menuId) {
		SysMenuEntity menu = sysMenuService.queryObject(menuId);
		return Result.successResult(menu);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:menu:save")
	public @ResponseBody Result save(@RequestBody SysMenuEntity menu) {
		// 数据校验
		try {
			verifyForm(menu);
			sysMenuService.save(menu);
			return Result.successResult(null);
		} catch (CException e) {
			return ExceptionSupport.resolverResult("保存菜单", this.getClass(), e);
		}

	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:menu:update")
	public @ResponseBody Result update(@RequestBody SysMenuEntity menu) {
		// 数据校验
		try {
			verifyForm(menu);
			sysMenuService.update(menu);
			return Result.successResult(null);
		} catch (CException e) {
			return ExceptionSupport.resolverResult("修改菜单", this.getClass(), e);
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	public @ResponseBody Result delete(@RequestBody String[] menuIds) {
		try {
			for (String menuId : menuIds) {
				if (Long.parseLong(menuId) <= 28) {
					return Result.failResult("系统菜单，不能删除");
				}
			}
			sysMenuService.deleteBatch(menuIds);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("修改菜单", this.getClass(), e);
		}
	}

	/**
	 * 用户菜单列表
	 */
	@RequestMapping("/user")
	public @ResponseBody Result user() {
		try {
			List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(ShiroUtils.getUserId());
			return Result.successResult(menuList);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("用户菜单列表", this.getClass(), e);
		}
	}

	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu) {
		if (StringUtils.isEmpty(menu.getName())) {
			throw new CException("菜单名称不能为空");
		}

		if (menu.getParentId() == null) {
			throw new CException("上级菜单不能为空");
		}

		// 菜单
		if (menu.getType() == 1) {
			if (StringUtils.isEmpty(menu.getUrl())) {
				throw new CException("菜单URL不能为空");
			}
		}

		// 上级菜单类型
		int parentType = 0;
		if (menu.getParentId() != 0) {
			SysMenuEntity parentMenu = sysMenuService.queryObject(menu.getParentId());
			parentType = parentMenu.getType();
		}

		// 目录、菜单
		if (menu.getType() == 0 || menu.getType() == 1) {
			if (parentType != 0) {
				throw new CException("上级菜单只能为目录类型");
			}
			return;
		}

		// 按钮
		if (menu.getType() == 2) {
			if (parentType != 1) {
				throw new CException("上级菜单只能为菜单类型");
			}
			return;
		}
	}
}
