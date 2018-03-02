package com.tamguo.web.admin;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.ArrayUtils;

import com.github.pagehelper.Page;
import com.tamguo.util.ExceptionSupport;
import com.tamguo.util.Result;
import com.tamguo.util.ShiroUtils;
import com.tamguo.model.SysUserEntity;
import com.tamguo.service.ISysUserRoleService;
import com.tamguo.service.ISysUserService;

@Controller
@RequestMapping(value = "admin/sysUser")
public class SysUserController {

	@Autowired
	private ISysUserService sysUserService;

	@Autowired
	private ISysUserRoleService sysUserRoleService;

	/**
	 * 获取管理员集合
	 * 
	 * @param sysUser
	 * @param page
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> ListAction(SysUserEntity sysUser, Integer page, Integer limit,
			ModelAndView model) {
		page = page == null ? 0 : page;
		limit = limit == null ? 10 : limit;
		Page<SysUserEntity> listSysUser = sysUserService.listSysUser(sysUser, page, limit);
		List<SysUserEntity> result = listSysUser.getResult();
		return Result.jqGridResult(result, listSysUser.getTotal(), limit, page, listSysUser.getPages());
	}

	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info")
	public @ResponseBody Result info() {
		return Result.successResult(ShiroUtils.getUser());
	}

	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public @ResponseBody Result info(@PathVariable("userId") Long userId) {
		SysUserEntity user = sysUserService.selectById(userId);
		// 获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		return Result.successResult(user);
	}

	/**
	 * 保存用户
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public @ResponseBody Result save(@RequestBody SysUserEntity user) {
		if (StringUtils.isEmpty(user.getUsername())) {
			return Result.failResult("用户名不能为空");
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			return Result.failResult("密码不能为空");
		}
		try {
			sysUserService.save(user);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("保存用户", this.getClass(), e);
		}

	}

	/**
	 * 修改用户
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public @ResponseBody Result update(@RequestBody SysUserEntity user) {
		if (StringUtils.isEmpty(user.getUsername())) {
			return Result.failResult("用户名不能为空");
		}
		try {
			sysUserService.update(user);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("修改用户", this.getClass(), e);
		}
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public @ResponseBody Result delete(@RequestBody String[] userIds) {
		if (ArrayUtils.contains(userIds, 1L)) {
			return Result.failResult("系统管理员不能删除");
		}

		if (ArrayUtils.contains(userIds, ShiroUtils.getUserId())) {
			return Result.failResult("当前用户不能删除");
		}
		try {
			sysUserService.deleteBatch(userIds);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("删除用户", this.getClass(), e);
		}
	}

}