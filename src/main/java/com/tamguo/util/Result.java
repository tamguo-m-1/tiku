package com.tamguo.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result implements Serializable {

	private static final long serialVersionUID = -1651614836984397356L;

	private int code;

	private Object result;

	private String message;

	public static final int SUCCESS_CODE = 0;

	public static final int FAIL_CODE = 1;

	private Result() {
	}

	private Result(int code, Object result, String message) {
		this.code = code;
		this.result = result;
		this.message = message;
	}

	/**
	 * 成功响应
	 * 
	 * @param result
	 * @return
	 */
	public static Result successResult(Object result) {
		return result(SUCCESS_CODE, result, "");
	}

	public static Result successResult(Object records, Long recordSum, Long rowsOfPage) {
		return successResult(records, recordSum, rowsOfPage, null);
	}

	public static Result successResult(Object records, Long recordSum, Long rowsOfPage, Object userData) {
		Map<String, Object> result = resultOfList(records, recordSum, rowsOfPage, userData);

		return successResult(result);
	}

	public static Map<String, Object> resultOfList(Object records, Long recordSum, Long rowsOfPage) {
		return resultOfList(records, recordSum, rowsOfPage, null);
	}

	public static Map<String, Object> resultOfList(Object Obj, Long records, Long rowsOfPage, Object userData) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", Obj);
		result.put("records", records);
		result.put("total", rowsOfPage);
		if (null != userData) {
			result.put("userdata", userData);
		}
		;
		return result;
	}

	public static Map<String, Object> jqGridResult(List<?> list, long totalCount, int pageSize, int currPage,
			int totalPage) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("totalCount", totalCount);
		result.put("pageSize", pageSize);
		result.put("currPage", currPage);
		result.put("totalPage", totalPage);
		return result;
	}

	/**
	 * 失败响应
	 * 
	 * @param errorMsg
	 * @return
	 */
	public static Result failResult(String errorMsg) {
		return result(FAIL_CODE, "", errorMsg);
	}

	public static Result result(int code, Object result, String message) {
		Result res = new Result(code, result, message);
		return res;
	}

	public int getCode() {
		return code;
	}

	public Object getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

}
