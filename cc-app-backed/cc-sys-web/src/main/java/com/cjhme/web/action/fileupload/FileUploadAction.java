package com.cjhme.web.action.fileupload;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * <p>
 * Title: FileUploadAction.java
 * </p>
 * Description: 文件上传
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/fileUpload")
public class FileUploadAction {

	Log LOGGER = LogFactory.getLog(FileUploadAction.class);


}
