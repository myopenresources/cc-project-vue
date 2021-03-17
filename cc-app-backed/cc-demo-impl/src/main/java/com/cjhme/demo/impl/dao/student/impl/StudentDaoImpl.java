package com.cjhme.demo.impl.dao.student.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.demo.common.model.DemoBean;
import com.cjhme.demo.impl.base.dao.BaseDao;
import com.cjhme.demo.impl.dao.student.StudentDao;

@Repository("demo.studentDao")
public class StudentDaoImpl extends BaseDao implements StudentDao {

	public DataPaging<Object> queryStudentByConditionPaging(DataPaging<Object> pageParameter){
		return this.selectPaging("com.cjhme.demo.impl.dao.student.StudentDao.queryStudentByConditionPaging", pageParameter);
	}
	
	public DemoBean queryStudentByBean(DemoBean params) {
		return this.sqlSessionTemplate.selectOne("com.cjhme.demo.impl.dao.student.StudentDao.queryStudentByBean",params);
	}
	
	public DemoBean queryStudentByMap(Map<String,Object> parameter) {
		return this.sqlSessionTemplate.selectOne("com.cjhme.demo.impl.dao.student.StudentDao.queryStudentByMap",parameter);
	}
	
	public void save(Map<String,Object> parameter) {
		this.sqlSessionTemplate.insert("com.cjhme.demo.impl.dao.student.StudentDao.save",parameter);
	}
}
