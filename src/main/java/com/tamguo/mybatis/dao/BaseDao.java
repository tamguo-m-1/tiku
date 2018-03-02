package com.tamguo.mybatis.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tamguo.mybatis.utils.Condition;
import com.tamguo.mybatis.utils.Page;


public interface BaseDao<T> {
	/**
	 * 删除
	 * 
	 * @param id
	 */
	int delete(String id);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	T select(String id);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<T> selectAll();

	/**
	 * 插入
	 * 
	 * @param t
	 */
	int insert(T t);

	/**
	 * 更新
	 * 
	 * @param t
	 */
	int update(T t);

	/**
	 * 根据id列表查询
	 * 
	 * @param idList
	 * @return
	 */
	List<T> selectByIds(List<String> idList);

	/**
	 * 根据对象查询，所有不为null的字段都是查询条件(时间除外)
	 * 
	 * @param t
	 * @return
	 */
	List<T> selectByEntity(T t);

	/**
	 * 根据id列表删除
	 * 
	 * @param idList
	 * @return
	 */
	int deleteByIds(List<String> idList);

	/**
	 * 分页查询
	 * 
	 * @param List
	 * @return
	 */
	List<T> selectByPage(Page<T> page);

	/**
	 * 条件查询
	 * 
	 * @param condition
	 * @return
	 */
	List<T> selectByCondition(Condition<T> condition);

	/**
	 * 根据条件更新
	 * 
	 * @param t
	 * @param condition
	 */
	int updateByCondition(@Param("t") T t, @Param("c") Condition<T> condition);

	/**
	 * 根据条件删除
	 * 
	 * @param condition
	 */
	int deleteByCondition(Condition<T> condition);

}
