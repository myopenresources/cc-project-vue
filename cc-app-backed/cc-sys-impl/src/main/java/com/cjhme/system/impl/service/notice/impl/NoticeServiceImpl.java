package com.cjhme.system.impl.service.notice.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.notice.NoticeBean;
import com.cjhme.system.impl.dao.notice.NoticeDao;
import com.cjhme.system.impl.service.notice.NoticeService;

/**
 * 
 * <p>
 * Title: NoticeServiceImpl.java
 * </p>
 * Description: 公告
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Service("system.noticeService")
public class NoticeServiceImpl implements NoticeService {

	private NoticeDao noticeDao;

	
	/**
	 * 根据条件分页查询系统公告
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> querySysNoticeByConditionPaging(DataPaging<Object> pageParameter){
		return this.noticeDao.querySysNoticeByConditionPaging(pageParameter);
	}
	
	/**
	 * 
	 * 保存系统公告
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveSysNotice(Map<String,Object> parameter){
		return this.noticeDao.saveNotice(parameter);
	}
	
	/**
	 * 
	 * 根据id查询系统公告 
	 * @param parameter
	 * @return
	 */
	public NoticeBean querySysNoticeById(Map<String,Object> parameter){
		return this.noticeDao.queryNoticeById(parameter);
	}
	
	/**
	 * 根据id修改系统公告
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateSysNoticeById(Map<String,Object> parameter){
		return this.noticeDao.updateNoticeById(parameter);
	}
	
	/**
	 * 根据ids删除系统公告 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int deleteSysNoticeByIds(Map<String,Object> parameter){
		return this.noticeDao.deleteNoticeByIds(parameter);
	}
	
	/**
	 * 根据用户id查看公告列表
	 * @param parameter
	 * @return
	 */
	public List<NoticeBean> queryNoticeByUserId(Map<String,Object> parameter){
		return this.noticeDao.queryNoticeByUserId(parameter);
	}
	
	/**
	 * 根据用户id查询分页公告列表
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryUserNoticeByConditionPaging(DataPaging<Object> pageParameter){
		return this.noticeDao.queryUserNoticeByConditionPaging(pageParameter);
	}
	
	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	@Resource(name="system.noticeDao")
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

}
