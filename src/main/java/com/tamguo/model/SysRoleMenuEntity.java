package com.tamguo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="sys_role_menu")
@NamedQuery(name="SysRoleMenuEntity.findAll", query="SELECT a FROM sys_role_menu a")
public class SysRoleMenuEntity implements Serializable {
	private static final long serialVersionUID = -3581364617124051920L;

	@Id
    private Long id;

	@Column(name="role_id")
    private Long roleId;

	@Column(name="menu_id")
    private Long menuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}