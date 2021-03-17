package com.cjhme.console.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cjhme.common.annotation.Auth;
import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.model.accessurl.AccessUrlBean;
import com.cjhme.common.model.role.RoleBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.system.impl.base.mybatis.datapermissions.interactive.DataPermissionsInteractive;

/**
 * 
 * <p>
 * Title: AuthInterceptor.java
 * </p>
 * Description: 权限拦截器
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	Log LOGGER = LogFactory.getLog(AuthInterceptor.class);

	/**
	 * 验证权限
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HandlerMethod method = (HandlerMethod) handler;
		Auth auth = method.getMethod().getAnnotation(Auth.class);

		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);

		// 对登录超时与默认验证进行验证
		if (auth == null || auth.verifyLogin()) {
			// UserBean userBean = (UserBean)
			// session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null == userBean || userBean.getUserId() == null) {
				LOGGER.info("验证登录超时");
				response.setHeader("session-status", "timeout");
				response.getWriter().print(CommonConstants.LOING_EXPIRED_OR_NOT_LOGIN_JSON);
				return false;
			}
		}

		// 对访问url进行验证
		if (auth == null || auth.verifyUrl()) {
			// UserBean userBean = (UserBean)
			// session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			List<RoleBean> roleList = userBean.getRoles();

			// 判断是否分配角色
			if (null != roleList && roleList.size() > 0) {

				boolean hasValid = false;

				for (RoleBean roleBean : roleList) {
					List<AccessUrlBean> accessUrls = roleBean.getAccessUrls();
					// 查询是否分配url
					if (null != accessUrls && accessUrls.size() > 0) {
						for (AccessUrlBean accessUrlBean : accessUrls) {
							if (-1 != request.getRequestURL().indexOf(accessUrlBean.getAccessUrl())) {
								hasValid = true;
								break;
							}
						}
					}

					if (hasValid) {
						break;
					}
				}

				// 验证未通过
				if (!hasValid) {
					LOGGER.info("验证访问url未分配权限");
					response.getWriter().print(CommonConstants.AUTHORITY_NO_AUTHORITY);
					return false;
				}

			} else {
				LOGGER.info("验证访问url未分配角色");
				response.getWriter().print(CommonConstants.AUTHORITY_UNDISTRIBUTED_ROLE);
				return false;
			}

		}

		// 登录的用户才可以做数据权限
		if (null != userBean) {
			DataPermissionsInteractive.setDataPermissionsAccessUrl(request.getServletPath());
			DataPermissionsInteractive.setDataPermissionsUserInfo(userBean);
		}

		return super.preHandle(request, response, handler);

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		
		//清除数据权限参数
		DataPermissionsInteractive.cleantDataPermissionsParams();
	}

}
