package com.tamguo.mybatis.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class SpringBeanUtils implements ApplicationContextAware {
	private static ApplicationContext ctx;

	public static Object getBean(String id) {
		if (ctx == null) {
			throw new NullPointerException("ApplicationContext is null");
		}
		return ctx.getBean(id);
	}

	public static <T> T getBean(Class<T> clazz) {
		if (ctx == null) {
			throw new NullPointerException("ApplicationContext is null");
		}
		return ctx.getBean(clazz);
	}

	public void setApplicationContext(ApplicationContext applicationcontext) throws BeansException {
		ctx = applicationcontext;
	}

}