package com.tamguo.web.admin;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.tamguo.model.SysRoleEntity;
import com.tamguo.service.ISysRoleMenuService;
import com.tamguo.service.ISysRoleService;
import com.tamguo.util.ExceptionSupport;
import com.tamguo.util.Result;

/**
 * 角色管理
 */
@RestController
@RequestMapping("admin/sysRole")
public class SysRoleController {
	@Autowired
	private ISysRoleService sysRoleService;
	@Autowired
	private ISysRoleMenuService sysRoleMenuService;


	/**
	 * 角色列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:role:list")
	public @ResponseBody Map<String, Object> list(String roleName, Integer page, Integer limit) {
		SysRoleEntity role = new SysRoleEntity();
		role.setRoleName(roleName);
		page = page == null ? 0 : page;
		limit = limit == null ? 10 : limit;
		// 查询列表数据
		Page<SysRoleEntity> list = sysRoleService.queryList(role, page, limit);
		List<SysRoleEntity> result = list.getResult();
		return Result.jqGridResult(result, list.getTotal(), limit, page, list.getPages());

	}

	/**
	 * 角色列表
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:role:select")
	public @ResponseBody Result select() {
		// 查询列表数据
		List<SysRoleEntity> list = sysRoleService.queryList(new SysRoleEntity(), 1, 10000);
		return Result.successResult(list);
	}

	/**
	 * 角色信息
	 */
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public @ResponseBody Result info(@PathVariable("roleId") Long roleId) {
		SysRoleEntity role = sysRoleService.queryObject(roleId);

		// 查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);

		return Result.successResult(role);
	}

	/**
	 * 保存角色
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("sys:role:save")
	public @ResponseBody Result save(@RequestBody SysRoleEntity role) {
		if (StringUtils.isEmpty(role.getRoleName())) {
			return Result.failResult("角色名称不能为空");
		}
		try {
			sysRoleService.save(role);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("保存角色", this.getClass(), e);
		}
	}

	/**
	 * 修改角色
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:role:update")
	public @ResponseBody Result update(@RequestBody SysRoleEntity role) {
		if (StringUtils.isEmpty(role.getRoleName())) {
			return Result.failResult("角色名称不能为空");
		}
		try {
			sysRoleService.update(role);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("修改角色", this.getClass(), e);
		}
	}

	/**
	 * 删除角色
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public @ResponseBody Result delete(@RequestBody String[] roleIds) {
		try {
			sysRoleService.deleteBatch(roleIds);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("删除角色", this.getClass(), e);
		}
	}
}
