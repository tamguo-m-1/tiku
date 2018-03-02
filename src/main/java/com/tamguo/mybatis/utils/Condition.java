package com.tamguo.mybatis.utils;

import java.lang.reflect.Field;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.util.StringUtils;

public abstract class Condition<T> {
	private String orderByClause;

	private String order;

	protected boolean distinct;

	protected List<Criterion> criteria;

	private Map<String, Field> map;
	private static final String EQ = " %s = ";
	private static final String NOT_EQ = " %s <> ";
	private static final String GT = " %s > ";
	private static final String LT = " %s < ";
	private static final String GTE = " %s >= ";
	private static final String LTE = " %s <= ";
	private static final String IN = " %s in ";
	private static final String NOT_IN = " %s in ";
	private static final String BETWEEN = " %s between ";
	private static final String NOT_BETWEEN = " %s not between ";
	private static final String LIKE = " %s like ";

	public Condition() {
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
		if (actualTypeArguments == null || actualTypeArguments.length == 0) {
			throw new RuntimeException(" unknow generic type ");
		}
		@SuppressWarnings("rawtypes")
		Field[] declaredFields = ((Class) actualTypeArguments[0]).getDeclaredFields();
		map = new HashMap<String, Field>(declaredFields.length);
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(Id.class)) {
				map.put(field.getName(), field);
			}
		}
		criteria = new ArrayList<Criterion>();
	}

	public void clear() {
		this.criteria.clear();
		this.setOrderByClause(null);
		this.distinct = false;
	}

	public Condition<T> orderBy(String orderByClause) {
		String[] split = null;
		if (!StringUtils.isEmpty(orderByClause)) {
			split = orderByClause.split(",");
			if (split != null && split.length > 0) {
				StringBuilder sb = new StringBuilder(orderByClause.length());
				for (String orderBy : split) {
					String columnName = getColumnName(orderBy);
					sb.append(columnName).append(",");
				}
				sb.deleteCharAt(sb.lastIndexOf(","));
				this.setOrderByClause(sb.toString());
			}
		}
		return this;
	}

	public Condition<T> desc() {
		this.setOrder("desc");
		return this;
	}

	public Condition<T> asc() {
		this.setOrder("asc");
		return this;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public boolean isValid() {
		return criteria.size() > 0;
	}

	public List<Criterion> getAllCriteria() {
		return criteria;
	}

	public List<Criterion> getCriteria() {
		return criteria;
	}

	protected void addCriterion(String condition) {
		if (condition == null) {
			throw new RuntimeException("Value for condition cannot be null");
		}
		criteria.add(new Criterion(condition));
	}

	protected void addCriterion(String condition, Object value, String property) {
		if (value == null) {
			throw new RuntimeException("Value for " + property + " cannot be null");
		}
		criteria.add(new Criterion(condition, value));
	}

	protected void addCriterion(String condition, Object value1, Object value2, String property) {
		if (value1 == null || value2 == null) {
			throw new RuntimeException("Between values for " + property + " cannot be null");
		}
		criteria.add(new Criterion(condition, value1, value2));
	}

	public Condition<T> andIsNull(String propertyName) {
		String name = getColumnName(propertyName);
		if (!StringUtils.isEmpty(name)) {
			addCriterion(name + " is null ");
		}
		return this;
	}

	public Condition<T> andIsNotNull(String propertyName) {
		String name = getColumnName(propertyName);
		if (!StringUtils.isEmpty(name)) {
			addCriterion(name + " is not null ");
		}
		return this;
	}

	public Condition<T> andEqualTo(String propertyName, Object value) {
		String name = getColumnName(propertyName);
		if (!StringUtils.isEmpty(name)) {
			addCriterion(String.format(EQ, name), value, propertyName);
		}
		return this;
	}

	public Condition<T> andNotEqualTo(String propertyName, Object value) {
		String name = getColumnName(propertyName);
		addCriterion(String.format(NOT_EQ, name), value, propertyName);
		return this;
	}

	public Condition<T> andGreaterThan(String propertyName, Object value) {
		String name = getColumnName(propertyName);
		addCriterion(String.format(GT, name), value, propertyName);
		return this;
	}

	public Condition<T> andGreaterThanOrEqualTo(String propertyName, Object value) {
		String name = getColumnName(propertyName);
		addCriterion(String.format(GTE, name), value, propertyName);
		return this;
	}

	public Condition<T> andLessThan(String propertyName, Object value) {
		String name = getColumnName(propertyName);
		addCriterion(String.format(LT, name), value, propertyName);
		return this;
	}

	public Condition<T> andLessThanOrEqualTo(String propertyName, Object value) {
		String name = getColumnName(propertyName);
		addCriterion(String.format(LTE, name), value, propertyName);
		return this;
	}

	public Condition<T> andIn(String propertyName, List<Object> values) {
		String name = getColumnName(propertyName);
		addCriterion(String.format(IN, name), values, propertyName);
		return this;
	}

	public Condition<T> andNotIn(String propertyName, List<Long> values) {
		String name = getColumnName(propertyName);
		addCriterion(String.format(NOT_IN, name), values, propertyName);
		return this;
	}

	public Condition<T> andBetween(String propertyName, Object value1, Object value2) {
		String name = getColumnName(propertyName);
		addCriterion(String.format(BETWEEN, name), value1, value2, propertyName);
		return this;
	}

	public Condition<T> andNotBetween(String propertyName, Object value1, Object value2) {
		String name = getColumnName(propertyName);
		addCriterion(String.format(NOT_BETWEEN, name), value1, value2, propertyName);
		return this;
	}

	public Condition<T> andLike(String propertyName, Object value) {
		String name = getColumnName(propertyName);
		addCriterion(String.format(LIKE, name), value, propertyName);
		return this;
	}

	private String getColumnName(String propertyName) {
		if (!map.containsKey(propertyName)) {
			throw new RuntimeException("there is no property " + propertyName + " or not be annotationed ");
		}
		Field field = map.get(propertyName);
		if (field.isAnnotationPresent(Column.class)) {
			return field.getAnnotation(Column.class).name();
		} else if (field.isAnnotationPresent(Id.class)) {
			return field.getName();
		}
		return null;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public static class Criterion {
		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private boolean likeValue;

		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (condition.endsWith("like ")) {
				this.likeValue = true;
			} else if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}

		public boolean isLikeValue() {
			return likeValue;
		}
	}
}
