package com.cjhme.common.model.fileupload;

import com.cjhme.common.model.base.BaseBean;

/**
 * 
 * <p>
 * Title: FileDataBean.java
 * </p>
 * Description: 文件数据
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class FileDataBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件id
	 */
	private String fileId;

	/**
	 * 业务标识
	 */
	private String busniessMark;

	/**
	 * 业务id
	 */
	private String busniessId;

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * 文件地址
	 */
	private String fileUrl;

	/**
	 * 文件描述
	 */
	private String fileDescript;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getBusniessMark() {
		return busniessMark;
	}

	public void setBusniessMark(String busniessMark) {
		this.busniessMark = busniessMark;
	}

	public String getBusniessId() {
		return busniessId;
	}

	public void setBusniessId(String busniessId) {
		this.busniessId = busniessId;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileDescript() {
		return fileDescript;
	}

	public void setFileDescript(String fileDescript) {
		this.fileDescript = fileDescript;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
