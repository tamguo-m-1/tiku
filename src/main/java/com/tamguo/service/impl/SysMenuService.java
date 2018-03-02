package com.tamguo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tamguo.dao.SysMenuMapper;
import com.tamguo.dao.SysUserRoleMapper;
import com.tamguo.model.SysMenuEntity;
import com.tamguo.mybatis.utils.Condition;
import com.tamguo.service.ISysMenuService;
import com.tamguo.dao.redis.CacheService;


@Service
public class SysMenuService implements ISysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    
    @Autowired
	private CacheService cacheService;

    @Override
    public Page<SysMenuEntity> queryList(Map<String, Object> map,int pageNum,int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);  
        Page<SysMenuEntity> pageList = (Page<SysMenuEntity>) sysMenuMapper.queryList(map);
        return pageList;
    }
    
    
    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuEntity> menuList = sysMenuMapper.queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }
        
        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for(SysMenuEntity menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
      //系统管理员，拥有最高权限
        if(userId == 1){
            return getAllMenuList(null);
        }
        //用户菜单列表
        List<Long> menuIdList = sysUserRoleMapper.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }
    
    
    
    /**
     * 获取所有菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);
        
        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){
        List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();
        
        for(SysMenuEntity entity : menuList){
            if(entity.getType() == 0){//目录
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        
        return subMenuList;
    }


	@Override
	public SysMenuEntity getMenuByUrl(String url) {
		SysMenuEntity sysMenuEntity = null;
		Object object = cacheService.getObject("sys_menu");
		if(object == null){
			Condition<SysMenuEntity> condition = new Condition<SysMenuEntity>(){};
			condition.andEqualTo("url", url).andEqualTo("type", 1);
			List<SysMenuEntity> menuList = sysMenuMapper.selectByCondition(condition);
			if(!menuList.isEmpty()){
				sysMenuEntity = menuList.get(0);
				cacheService.setObject("sys_menu", sysMenuEntity);
			}
		}else{
			sysMenuEntity = (SysMenuEntity) object;
		}
		return sysMenuEntity;
	}


	@Override
	public List<SysMenuEntity> queryNotButtonList() {
		return sysMenuMapper.queryNotButtonList();
	}


	@Override
	public SysMenuEntity queryObject(Long menuId) {
		return sysMenuMapper.select(String.valueOf(menuId));
	}

	@Override
	public void save(SysMenuEntity menu) {
		sysMenuMapper.insert(menu);		
	}

	@Override
	public void update(SysMenuEntity menu) {
		sysMenuMapper.update(menu);		
	}

	@Override
	public void deleteBatch(String[] menuIds) {
		sysMenuMapper.deleteByIds(Arrays.asList(menuIds));	
	}

    
}
