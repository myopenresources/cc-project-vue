package com.cjhme.system.impl.service.notice;

import java.util.List;
import java.util.Map;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.notice.NoticeBean;

/**
 * 
 * <p>  
 * Title: NoticeService.java 
 * </p>  
 * Description: 公告
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public interface NoticeService {

	/**
	 * 根据条件分页查询系统公告
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> querySysNoticeByConditionPaging(DataPaging<Object> pageParameter);
	
	/**
	 * 
	 * 保存系统公告
	 * @param parameter
	 * @return
	 */
	public int saveSysNotice(Map<String,Object> parameter);
	
	
	/**
	 * 根据id修改系统公告
	 * @param parameter
	 * @return
	 */
	public int updateSysNoticeById(Map<String,Object> parameter);
	
	/**
	 * 根据ids删除系统公告 
	 * @param parameter
	 * @return
	 */
	public int deleteSysNoticeByIds(Map<String,Object> parameter);
	
	/**
	 * 
	 * 根据id查询系统公告 
	 * @param parameter
	 * @return
	 */
	public NoticeBean querySysNoticeById(Map<String,Object> parameter);
	
	/**
	 * 根据用户id查看公告列表
	 * @param parameter
	 * @return
	 */
	public List<NoticeBean> queryNoticeByUserId(Map<String,Object> parameter);
	
	/**
	 * 根据用户id查询分页公告列表
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryUserNoticeByConditionPaging(DataPaging<Object> pageParameter);
	
}
