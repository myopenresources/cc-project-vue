package com.cjhme.common.model.notice;

import com.cjhme.common.model.base.BaseBean;

/**
 * 
 * <p>  
 * Title: NoticeBean.java 
 * </p>  
 * Description: 公告
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public class NoticeBean extends BaseBean{

	private static final long serialVersionUID = 1L;

	/**
	 * 公告id
	 */
	private String noticeId;
	
	/**
	 * 公告标题 
	 */
	private String noticeTitle;
	
	/**
	 * 公告内容
	 */
	private String noticeContent;
	
	/**
	 * 公告类型(1 系统公告，2部门公告，3角色公告）
	 */
	private String noticeType;
	
	/**
	 * 截止时间
	 */
	private String cutoffDate;

	
	public String getCutoffDate() {
		return cutoffDate;
	}

	public void setCutoffDate(String cutoffDate) {
		this.cutoffDate = cutoffDate;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	
	

}
