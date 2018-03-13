package com.tamguo.dao;

import com.tamguo.model.MemberEntity;
import com.tamguo.mybatis.dao.BaseDao;

public interface MemberMapper extends BaseDao<MemberEntity>{

	MemberEntity findByUsername(String username);

}
