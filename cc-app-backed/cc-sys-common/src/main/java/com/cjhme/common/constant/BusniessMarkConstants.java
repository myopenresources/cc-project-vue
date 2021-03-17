package com.cjhme.common.constant;


import com.cjhme.common.annotation.BusniessMarkDesc;

/**
 * 
 * <p>  
 * Title: BusniessMarkBean.java 
 * </p>  
 * Description: 业务标识常量
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public class BusniessMarkConstants {
	
	
	/**
	 * 系统公共
	 */
	@BusniessMarkDesc(description="系统公共")
	public static String SYS_COMMON="SYS_COMMON";
	
	/**
	 * 用户头像
	 */
	@BusniessMarkDesc(description="用户头像")
	public static String USER_HEAD_IMG="USER_HEAD_IMG";
	
	
	/**
	 * 用户管理
	 */
	@BusniessMarkDesc(description="用户管理")
	public static String USER_MANAGE="USER_MANAGE";
	
	/**
	 * 部门管理
	 */
	@BusniessMarkDesc(description="部门管理")
	public static String DEPT_MANAGE="DEPT_MANAGE";
	
	/**
	 * 角色管理
	 */
	@BusniessMarkDesc(description="角色管理")
	public static String ROLE_MANAGE="ROLE_MANAGE";
	 
	/**
	 * 菜单管理
	 */
	@BusniessMarkDesc(description="菜单管理")
	public static String MENU_MANAGE="MENU_MANAGE";
	
	/**
	 * 按钮管理
	 */
	@BusniessMarkDesc(description="按钮管理")
	public static String BTN_MANAGE="BTN_MANAGE";
	
	/**
	 * 数据字典管理
	 */
	@BusniessMarkDesc(description="数据字典管理")
	public static String DATA_DIC_MANAGE="DATA_DIC_MANAGE";
	
	/**
	 * 系统日志管理
	 */
	@BusniessMarkDesc(description="系统日志管理")
	public static String SYS_LOG_MANAGE="SYS_LOG_MANAGE";
	
	/**
	 * 登录日志管理
	 */
	@BusniessMarkDesc(description="登录日志管理")
	public static String LOGIN_LOG_MANAGE="LOGIN_LOG_MANAGE";
	
	/**
	 * 用户待办管理
	 */
	@BusniessMarkDesc(description="用户待办管理")
	public static String USER_TASK_MANAGE="USER_TASK_MANAGE";
	
	/**
	 * 访问管理
	 */
	@BusniessMarkDesc(description="访问管理")
	public static String ACCESS_URL_MANAGE="ACCESS_URL_MANAGE";
	
	/**
	 * 系统公告管理
	 */
	@BusniessMarkDesc(description="系统公告管理")
	public static String SYS_NOTICE_MANAGE="SYS_NOTICE_MANAGE";
	
	/**
	 * 用户公告管理
	 */
	@BusniessMarkDesc(description="用户公告管理")
	public static String USER_NOTICE_MANAGE="USER_NOTICE_MANAGE";
	
	/**
	 * 数据权限管理
	 */
	@BusniessMarkDesc(description="数据权限管理")
	public static String DATA_PERMISSIONS_MANAGE="DATA_PERMISSIONS_MANAGE";
	
	
	
	
	
	
	
	
	
	/**
	 * 查询业务标识列表
	 * @return
	 * @throws Exception
	 */
	/*public static List<Map<String,Object>>   queryBusniessMarkList() throws Exception{
		List<Map<String,Object>> busniessMarks = new ArrayList<Map<String,Object>>();
		Class<?> classType = BusniessMarkConstants.class;
		Field[] fields = classType.getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			Map<String,Object> item=new HashMap<String,Object>();
			item.put("busniessMarkVal", (String) fields[i].get(classType));
			
			BusniessMarkDesc desc = fields[i].getAnnotation(BusniessMarkDesc.class);
			if(null!=desc){
				item.put("busniessMarkDesc", desc.description());
			}else{
				item.put("busniessMarkDesc", "未配置描述");
			}
			
			busniessMarks.add(item);
		}
		
		return busniessMarks;
	}*/
	
	
	
	/**
	 * 测试
	 * @param args
	 */
	/*public static void main(String[] args) {
		try {
			List<Map<String,Object>> busniessMarks =BusniessMarkConstants.queryBusniessMarkList();
			for (int i = 0; i < busniessMarks.size(); i++) {
				System.out.println(busniessMarks.get(i).get("busniessMarkDesc"));
				System.out.println(busniessMarks.get(i).get("busniessMarkVal"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
