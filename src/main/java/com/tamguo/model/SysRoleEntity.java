package com.tamguo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="sys_role")
@NamedQuery(name="SysRoleEntity.findAll", query="SELECT a FROM sys_role a")
public class SysRoleEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="role_id")
    private Long roleId;

	@Column(name="role_name")
    private String roleName;

	@Column(name="remark")
    private String remark;

	@Column(name="create_time")
    private Long createTime;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    
    private List<Long> menuIdList;

	public List<Long> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}
}