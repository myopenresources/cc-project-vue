package com.cjhme.demo.impl.service.student;

import com.cjhme.common.model.base.DataPaging;

public interface StudentService {

	public DataPaging<Object> queryStudentByConditionPaging(DataPaging<Object> pageParameter);
	
	public void save();
}
