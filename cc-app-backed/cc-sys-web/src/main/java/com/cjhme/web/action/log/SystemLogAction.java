package com.cjhme.web.action.log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cjhme.common.constant.BusniessMarkConstants;
import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.model.base.ResultData;
import com.cjhme.common.model.button.ButtonBean;
import com.cjhme.common.model.log.SystemLogFileBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.util.ConfigUtil;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.system.impl.service.log.SystemLogService;
import com.cjhme.web.base.action.SysBaseAction;

/**
 * <p>
 * Title: SystemLogAction.java
 * </p>
 * Description: 系统日志
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/systemLog")
public class SystemLogAction extends SysBaseAction {

    Log LOGGER = LogFactory.getLog(SystemLogAction.class);

    private SystemLogService systemLogService;


    /**
     * 初始系统日志管理数据
     *
     * @return
     */
    @RequestMapping(value = "/initSystemLogManageData", method = RequestMethod.POST)
    @SuppressWarnings("unchecked")
    public void initSystemLogManageData(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultData resultData = new ResultData();
        try {
            Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
            UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
            if (null != userBean && !"".equals(userBean) && !"null".equals(userBean) && null != parameter) {
                Map<String, Object> map = new HashMap<String, Object>();

                parameter.put("userId", userBean.getUserId());
                parameter.put("busniessMark", BusniessMarkConstants.SYS_LOG_MANAGE);
                List<ButtonBean> buttonList = this.getBusniessBtn(parameter);
                map.put("buttonList", buttonList);

                List<SystemLogFileBean> systemLogFileList = this.systemLogService.querySystemLogFile();
                map.put("systemLogFileList", systemLogFileList);

                resultData.setResultType(CommonConstants.RESULT_SUCCESS);
                resultData.setResultData(map);
                LOGGER.info("初始日志管理数据成功！");
            } else {
                resultData.setResultType(CommonConstants.RESULT_FAILURE);
                resultData.setResultMsg("初始日志管理数据未成功！");
                LOGGER.error("初始日志管理数据未成功");
            }

        } catch (Exception e) {
            resultData.setResultType(CommonConstants.RESULT_ERROR);
            resultData.setResultMsg("初始日志管理数据失败！");
            e.printStackTrace();
            LOGGER.error("初始日志管理数据失败,错误——>" + e);
        }

        resultData.printJsonData(response);

    }



    /**
     * 系统日志下载
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadSystemLog", method = RequestMethod.GET)
    public void downloadSystemLog(HttpServletRequest request, HttpServletResponse response) {
        try {
            String param = request.getParameter("filePath");
            String systemLogPath = ConfigUtil.getInstance().getPopString("systemLogPath");
            if (!StringUtils.isEmpty(param) && !StringUtils.isEmpty(systemLogPath)) {
                String filePath = java.net.URLDecoder.decode(param, "UTF-8");

                File sysLog = new File(systemLogPath);
                File fileLog = new File(filePath);
                if(-1!=fileLog.getPath().indexOf(sysLog.getPath())){
                    try (
                            InputStream in = new FileInputStream(filePath.trim());
                    ) {
                        byte[] buffer = new byte[in.available()];
                        in.read(buffer);
                        in.close();

                        String fileName = "";
                        int index = 0;
                        if (-1 != filePath.lastIndexOf(".")) {
                            index = filePath.lastIndexOf(".");
                        }
                        fileName = UUID.randomUUID() + filePath.substring(index, filePath.length());

                        response.reset();

                        // 设置HTML头部信息
                        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName.trim() + "\"");
                        response.addHeader("Content-Length", "" + buffer.length);
                        response.setContentType("application/octet-stream;charset=UTF-8");

                        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                        outputStream.write(buffer);
                        outputStream.flush();
                        outputStream.close();
                        LOGGER.info("系统日志下载成功！");
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("系统日志下载失败,错误——>" + e);
        }

    }

    /**
     * 删除系统日志
     *
     * @param request
     * @param response
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/deleteSystemLog", method = RequestMethod.POST)
    public void deleteSystemLog(HttpServletRequest request, HttpServletResponse response) {
        ResultData resultData = new ResultData();
        try {
            Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
            String filePath = String.valueOf(parameter.get("filePath"));
            String systemLogPath = ConfigUtil.getInstance().getPopString("systemLogPath");
            if (!StringUtils.isEmpty(parameter) && !StringUtils.isEmpty(filePath) && !StringUtils.isEmpty(systemLogPath)) {
                String param = java.net.URLDecoder.decode(filePath, "UTF-8");

                File sysLog = new File(systemLogPath);
                File file = new File(param.trim());
                if(-1!=file.getPath().indexOf(sysLog.getPath())){

                    if (file.exists()) {
                        boolean result = file.delete();
                        if (result) {
                            resultData.setResultType(CommonConstants.RESULT_SUCCESS);
                            resultData.setResultMsg("删除系统日志成功！");
                            LOGGER.info("删除系统日志成功！");
                        } else {
                            resultData.setResultType(CommonConstants.RESULT_FAILURE);
                            resultData.setResultMsg("删除系统日志未成功，权限不足或文件正在使用！");
                            LOGGER.info("删除系统日志未成功！");
                        }
                    } else {
                        resultData.setResultType(CommonConstants.RESULT_FAILURE);
                        resultData.setResultMsg("系统日志不存在！");
                        LOGGER.info("系统日志不存在！");
                    }
                }else{
                    resultData.setResultType(CommonConstants.RESULT_FAILURE);
                    resultData.setResultMsg("非法的请求路径！");
                    LOGGER.info("非法的请求路径！");
                }

            } else {
                resultData.setResultType(CommonConstants.RESULT_FAILURE);
                resultData.setResultMsg("请求参数有误！");
                LOGGER.info("请求参数有误！");
            }
        } catch (Exception e) {
            resultData.setResultType(CommonConstants.RESULT_ERROR);
            resultData.setResultMsg("删除系统日志失败！");
            e.printStackTrace();
            LOGGER.error("删除系统日志失败,错误——>" + e);
        }

        resultData.printJsonData(response);
    }


    public SystemLogService getSystemLogService() {
        return systemLogService;
    }

    @Resource(name="system.systemLogService")
    public void setSystemLogService(SystemLogService systemLogService) {
        this.systemLogService = systemLogService;
    }

}
