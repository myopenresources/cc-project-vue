package com.cjhme.demo.impl.service.student.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.dept.DeptBean;
import com.cjhme.demo.common.model.DemoBean;
import com.cjhme.demo.impl.dao.student.StudentDao;
import com.cjhme.demo.impl.service.student.StudentService;
import com.cjhme.system.impl.base.mybatis.datapermissions.interactive.DataPermissionsInteractive;
import com.cjhme.system.impl.dao.user.UserDao;

@Service("demo.studentService")
public class StudentServiceImpl implements StudentService {

	@Resource(name = "demo.studentDao")
	private StudentDao studentDao;

	@Resource(name = "system.userDao")
	private UserDao userDao;

	/**
	 * 查询
	 */
	public DataPaging<Object> queryStudentByConditionPaging(DataPaging<Object> pageParameter) {
		// 使用基本的数据权限处理器
		DataPermissionsInteractive.usePrimaryDataPermissionsProcessor();
        List<DeptBean> designatedDepts = new ArrayList<>();
        
        DeptBean dept1 = new DeptBean();
        dept1.setLeftValue(3);
        dept1.setRightValue(4);
        
        DeptBean dept2 = new DeptBean();
        dept2.setLeftValue(8);
        dept2.setRightValue(9);
        
        designatedDepts.add(dept1);
        designatedDepts.add(dept2);
        
        Map<String,Object> parameter = new HashMap<>();
        parameter.put("designatedDepts",designatedDepts);
        
        //List<String> designatedUsers = new ArrayList<>();
        //designatedUsers.add("1aaaaaaaaaaaaaaaaaaaa");
        //Map<String,Object> parameter = new HashMap<>();
        //parameter.put("designatedUsers",designatedUsers);
        DataPermissionsInteractive.setDataPermissionsParameter(parameter);
        
        //DemoBean params = new DemoBean();
        //params.setId("1212121");
        //DemoBean demoBean1=this.studentDao.queryStudentByBean(params);
        
        //Map<String,Object> parameter2 = new HashMap<>();
        //parameter2.put("id", "1212121");
        //DemoBean demoBean2=this.studentDao.queryStudentByMap(parameter2);
        
		return this.studentDao.queryStudentByConditionPaging(pageParameter);
	}

	/**
	 * 保存
	 */
	@Transactional
	public void save() {
		Map<String, Object> demo = new HashMap<>();
		demo.put("id", "1212121");
		demo.put("name", "test");
		this.studentDao.save(demo);

		Map<String, Object> user = new HashMap<>();
		user.put("userId", "12121");
		user.put("userName", "test");
		user.put("loginName", "122222");
		user.put("password", "test");
		this.userDao.saveUser(user);

	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

}
