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
import com.tamguo.model.CourseEntity;
import com.tamguo.service.IChapterService;
import com.tamguo.service.ICourseService;
import com.tamguo.util.ExceptionSupport;
import com.tamguo.util.Result;

@Controller(value="adminCourseController")
public class TikuCourseController {
	
	@Autowired
	private ICourseService iCourseService;
	@Autowired
	private IChapterService iChapterService;
	
	@RequestMapping("admin/course/list")
	@RequiresPermissions("tiku:course:list")
	@ResponseBody
	public Map<String, Object> list(String name , Integer page , Integer limit){
		Page<CourseEntity> list = iCourseService.list(name, page, limit);
		return Result.jqGridResult(list.getResult(), list.getTotal(), limit, page, list.getPages());
	}
	
	@RequestMapping("admin/course/getChapterTree/{courseId}.html")
	@RequiresPermissions("tiku:course:list")
	@ResponseBody
	public Result getChapterTree(@PathVariable String courseId){
		return Result.result(0, iChapterService.getChapterTree(courseId), null);
	}
	
	@RequestMapping("admin/course/info/{courseId}.html")
	@RequiresPermissions("tiku:course:list")
	@ResponseBody
	public Result info(@PathVariable String courseId){
		try {
			return Result.result(0, iCourseService.select(courseId), null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("查询科目", this.getClass(), e);
		}
	}
	
	@RequestMapping("admin/course/delete.html")
	@RequiresPermissions("tiku:course:delete")
	@ResponseBody
	public Result delete(@RequestBody String[] courseIds){
		try {
			iCourseService.deleteByIds(courseIds);
			return Result.result(0, null, null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("查询科目", this.getClass(), e);
		}
	}
	
	@RequestMapping("admin/course/save.html")
	@RequiresPermissions("tiku:course:save")
	@ResponseBody
	public Result save(@RequestBody CourseEntity course){
		try {
			iCourseService.save(course);
			return Result.result(0, null, null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("保存科目", this.getClass(), e);
		}
	}
	
	@RequestMapping("admin/course/update.html")
	@RequiresPermissions("tiku:course:update")
	@ResponseBody
	public Result update(@RequestBody CourseEntity course){
		try {
			iCourseService.update(course);
			return Result.result(0, null, null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("保存科目", this.getClass(), e);
		}
	}
}
