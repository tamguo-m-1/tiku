package com.tamguo.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the tiku_chapter database table.
 * 
 */
@Entity
@Table(name="tiku_area")
@NamedQuery(name="AreaEntity.findAll", query="SELECT c FROM AreaEntity c")
public class AreaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String uid;

	@Column(name="name")
	private String name;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}