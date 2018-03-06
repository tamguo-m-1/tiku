package com.tamguo.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tamguo.model.CourseEntity;
import com.tamguo.model.MenuEntity;
import com.tamguo.model.PaperEntity;
import com.tamguo.model.SchoolEntity;
import com.tamguo.service.ICourseService;
import com.tamguo.service.IMenuService;
import com.tamguo.service.IPaperService;
import com.tamguo.service.ISchoolService;
import com.tamguo.util.Setting;
import com.tamguo.util.TamguoConstant;

/**
 * 菜单拦截
 *
 */
@Component
public class MenuInterceptor implements HandlerInterceptor {
	
	@Resource
	private IMenuService iSubjectService;
	@Resource
	private ICourseService iCourseService;
	@Resource
	private IPaperService iPaperService;
	@Resource
	private ISchoolService iSchoolService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //在请求处理之前进行调用（Controller方法调用之前）
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    	List<MenuEntity> result = iSubjectService.findMenus();
    	request.setAttribute("menuList", result);
    	
    	// 获取全部菜单
    	List<MenuEntity> allMenuList = iSubjectService.findAllMenus();
    	request.setAttribute("allMenuList", allMenuList);
    	
    	// 获取左侧菜单
    	List<MenuEntity> leftMenuList = iSubjectService.findLeftMenus();
    	request.setAttribute("leftMenuList", leftMenuList);

    	// 获取底部菜单
    	List<MenuEntity> footerMenuList = iSubjectService.findFooterMenus();
    	request.setAttribute("footerMenuList", footerMenuList);
    	
    	
    	// 获取首页专区
    	List<CourseEntity> courseList = iCourseService.findGaokaoArea(TamguoConstant.GAOKAO_SUBJECT_ID);
    	request.setAttribute("gaokaoCourseList", courseList);
    	
    	// 获取首页历年真题试卷
    	List<PaperEntity> historyPaperList = iPaperService.findHistoryPaper();
    	request.setAttribute("historyPaperList", historyPaperList);
    	
    	// 获取首页模拟试卷
    	List<PaperEntity> simulationPaperList = iPaperService.findSimulationPaper();
    	request.setAttribute("simulationPaperList", simulationPaperList);
    	
    	// 获取热门试卷
    	List<PaperEntity> hotPaperList = iPaperService.findHotPaper();
    	request.setAttribute("hotPaperList", hotPaperList);
    	
    	// 获取首页名校试卷
    	List<SchoolEntity> eliteSchoolPaperList = iSchoolService.findEliteSchoolPaper();
    	request.setAttribute("eliteSchoolPaperList", eliteSchoolPaperList);
    	
    	// 获取首页名校列表
    	List<SchoolEntity> eliteSchoolList = iSchoolService.findEliteSchool();
    	request.setAttribute("eliteSchoolList", eliteSchoolList);
    	
    	// 设置系统变量
    	request.setAttribute("setting", Setting.getSetting());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    	//在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    }

}