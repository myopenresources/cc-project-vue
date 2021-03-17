package com.cjhme.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cjhme.common.annotation.Auth;
import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.base.ResultData;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.demo.impl.service.student.StudentService;

@Controller
@Scope("prototype")
@RequestMapping("/student")
public class StudentAction {
	@Resource(name="demo.studentService")
	private StudentService studentService;

	/**
	 * 数据权限
	 * @param request
	 * @param response
	 */
	@Auth(verifyLogin = false, verifyUrl = false)
	@RequestMapping(value = "/queryStudentByConditionPaging", method = RequestMethod.POST)
	public void queryDemoByConditionPaging(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			DataPaging<Object> result = this.studentService.queryStudentByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			if (null != result) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("rows", result.getRows());
				map.put("total", result.getTotal());

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("未成功！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("失败！");
			e.printStackTrace();
		}

		resultData.printJsonData(response);
	}
	
    /**
     * 多数据源保存
     * @param session
     * @param request
     * @param response
     */
	@Auth(verifyLogin = false, verifyUrl = false)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void saveDemo(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			this.studentService.save();

			resultData.setResultType(CommonConstants.RESULT_SUCCESS);
			resultData.setResultMsg("成功！");
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("失败！");
			e.printStackTrace();
		}
		resultData.printJsonData(response);
	}
}
