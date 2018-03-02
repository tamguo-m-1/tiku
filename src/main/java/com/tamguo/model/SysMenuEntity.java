package com.tamguo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="sys_menu")
@NamedQuery(name="SysMenuEntity.findAll", query="SELECT a FROM sys_menu a")
public class SysMenuEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="menu_id")
    private Long menuId;

	@Column(name="parent_id")
    private Long parentId;

	@Column(name="name")
    private String name;

	@Column(name="url")
    private String url;

	@Column(name="perms")
    private String perms;

	@Column(name="type")
    private Integer type;

	@Column(name="icon")
    private String icon;

	@Column(name="order_num")
    private Integer orderNum;
    
    
    /**
     * ztree属性
     */
    private Boolean open;
    
    private List<?> list;
    
    private String parentName;
    

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms == null ? null : perms.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    
    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    

    public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}