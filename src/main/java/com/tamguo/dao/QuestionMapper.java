package com.tamguo.dao;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import com.tamguo.model.QuestionEntity;
import com.tamguo.mybatis.dao.BaseDao;

public interface QuestionMapper extends BaseDao<QuestionEntity>{

	Page<QuestionEntity> findByChapterId(@Param(value="chapterId")String chapterId);

}
