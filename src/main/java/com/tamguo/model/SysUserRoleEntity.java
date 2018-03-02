package com.tamguo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="sys_user_role")
@NamedQuery(name="SysUserRoleEntity.findAll", query="SELECT a FROM sys_user_role a")
public class SysUserRoleEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    private Long id;

	@Column(name="user_id")
    private Long userId;

	@Column(name="role_id")
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}