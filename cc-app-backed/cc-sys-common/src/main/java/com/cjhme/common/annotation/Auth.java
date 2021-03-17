package com.cjhme.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * <p>
 * Title: Auth.java
 * </p>
 * Description: 权限注解
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface Auth {

	/**
	 * 验证登录
	 * 
	 * @return
	 */
	public boolean verifyLogin() default true;
	
	/**
	 * 验证url
	 * 
	 * @return
	 */
	public boolean verifyUrl() default true;
}
