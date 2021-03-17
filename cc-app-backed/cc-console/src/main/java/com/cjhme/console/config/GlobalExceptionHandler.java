package com.cjhme.console.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * <p>
 * Title: GlobalExceptionHandler.java
 * </p>
 * Description: 全局异常处理
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    Log LOGGER = LogFactory.getLog(HandlerExceptionResolver.class);

    /**
     * 异常处理实现
     */
    @ExceptionHandler(value=Exception.class)
    public Map<String,Object> resolveException(Exception exception) throws Exception
    {
        Map<String,Object> errorResult = new HashMap<>();
        errorResult.put("message", exception.getMessage());
        errorResult.put("localizedMessage", exception.getLocalizedMessage());
        errorResult.put("cause", exception.getCause());
        errorResult.put("suppressed", exception.getSuppressed());
        //errorResult.put("stackTrace", exception.getStackTrace());

        LOGGER.error("系统发生异常，异常信息如下：\n"+exception.getMessage());
        exception.printStackTrace();
        return errorResult;
    }
}
