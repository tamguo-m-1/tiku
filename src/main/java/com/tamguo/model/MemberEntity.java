package com.tamguo.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the tiku_ad database table.
 * 
 */
@Entity
@Table(name="tiku_member")
@NamedQuery(name="MemberEntity.findAll", query="SELECT m FROM MemberEntity m")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String uid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="avatar")
	private String avatar;
	
	@Column(name="login_failure_count")
	private Integer loginFailureCount;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="email")
	private String email;
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}