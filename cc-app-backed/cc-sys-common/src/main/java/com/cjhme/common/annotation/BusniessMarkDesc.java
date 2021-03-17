package com.cjhme.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 描述：业务标识描述
 * @author cjh
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Documented
public @interface BusniessMarkDesc {

	/**
	 * 
	 * 描述：描述
	 * @author cjh
	 * @return
	 */
	String description() default "";
}
