package com.tamguo.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import com.tamguo.mybatis.enums.BaseEnum;

public class EnumTypeHandler extends BaseTypeHandler<BaseEnum> {
	private Class<BaseEnum> type;
	private BaseEnum[] enums;

	public EnumTypeHandler(Class<BaseEnum> type) {
		if (type == null)
			throw new IllegalArgumentException("Type argument cannot be null");
		this.type = type;
		this.enums = type.getEnumConstants();
		if (this.enums == null)
			throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
	}

	@Override
	public BaseEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int id = rs.getInt(columnName);
		if (rs.wasNull()) {
			return null;
		} else {
			return getEnumObjById(id);
		}

	}

	@Override
	public BaseEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int id = rs.getInt(columnIndex);
		if (rs.wasNull()) {
			return null;
		} else {
			return getEnumObjById(id);
		}
	}

	@Override
	public BaseEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int id = cs.getInt(columnIndex);
		if (cs.wasNull()) {
			return null;
		} else {
			return getEnumObjById(id);
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum baseEnum, JdbcType jdbcType)
			throws SQLException {
		System.out.println(baseEnum.getId());
		ps.setInt(i, baseEnum.getId());
	}

	private BaseEnum getEnumObjById(int id) {
		for (BaseEnum baseEnum : enums) {
			if (baseEnum.getId() == id) {
				return baseEnum;
			}
		}
		throw new IllegalArgumentException("未知的枚举类型：" + id + ",请核对" + type.getSimpleName());
	}

}
