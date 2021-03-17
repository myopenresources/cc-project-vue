package com.cjhme.demo.impl.dao.student;

import java.util.Map;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.demo.common.model.DemoBean;

public interface StudentDao {


	public DataPaging<Object> queryStudentByConditionPaging(DataPaging<Object> pageParameter);
	
	public DemoBean queryStudentByBean(DemoBean params);
	
	public DemoBean queryStudentByMap(Map<String,Object> parameter);
	
	public void save(Map<String,Object> parameter);
}
