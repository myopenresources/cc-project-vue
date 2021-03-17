package com.cjhme.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p>  
 * Title: ListToMapUtil.java 
 * </p>  
 * Description: list转换成map
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public class ListToMapUtil {

	/**
	 * list转换成map
	 * @param parameter
	 * @param keyMethod
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> list2Map(List<Object> parameter,String keyMethod) throws Exception{
		Map<String,Object> resultParameter = null;
		if(null!=parameter){
			resultParameter = new HashMap<String,Object>();
			for(Object item:parameter){
				
				 if(item==null){  
			       continue;  
			     }  
				 
				 String valueGetter = "get" + keyMethod.substring(0, 1).toUpperCase() + keyMethod.substring(1);
				 Object o=item.getClass().getDeclaredMethod(valueGetter).invoke(new Object[]{});
				 
				 if(null!=o){
					 String key = (String) o;
					 if (!resultParameter.containsKey(key)) {
						 resultParameter.put(key, item); 
					 } 
				 }else{
					 throw new Exception("keyMethod无法获取对应方法！");
				 }
				 
			        
			}
		}else{
		   throw new Exception("list转换成map参数空指针异常！");
		}
		
		return resultParameter;
	}
	
	
}
