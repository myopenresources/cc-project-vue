package com.cjhme.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


/**
 *
 * <p>
 * Title: CcApplication.java
 * </p>
 * Description: 应用入口
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
@SpringBootApplication
@ServletComponentScan
@ComponentScan({"com.cjhme"})
public class CcApplication {


	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CcApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CcApplication.class, args);
	}
}
