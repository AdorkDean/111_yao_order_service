package com.rc.openapi.util;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;





/**  
* @Title: MerKeyTool.java
* @Description: 取商户密钥工具，暂时一次全部取出，密钥有更改时，更新数据库是同时更新此类中的Map
* @author yinbinhome@163.com
* @date 2011-4-13 下午04:45:35
* @version V1.0  
*/
public class MerKeyTool extends HttpServlet{
	private Map<String,String> keyMap=new HashMap<String,String>();
	private static MerKeyTool mertool=null;
//	private MerKeyTool(){		
//		ServletContext context = config.getServletContext();
//		Map<String,String> keyp=(Map<String, String>) context.getAttribute("keyMap");
//		if(keyp==null){
//			ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext*.xml");				
//			MerchantkeyManager merManager=(MerchantkeyManager)act.getBean("merchantkeyManager");
//			
//			MerchantkeyExample mke=new MerchantkeyExample();		
//			List<Merchantkey> list=new ArrayList<Merchantkey>();;
//			try {
//				list = merManager.selectByRepository(mke);
//			} catch (Exception e) {			
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if(list!=null && list.size()>0){
//				for(Merchantkey mk:list){
//					keyMap.put(mk.getUserid().toString(), mk.getTradeKey());
//				}
//			}
//			context.setAttribute("keyMap", keyMap);
//			
//		}else{
//			keyMap=keyp;
//		}
//		
//	}
	
	public synchronized static MerKeyTool getIntance(){
		if(mertool==null){
			return new MerKeyTool();
		}else{
			return mertool;
		}
	}
	
	
	
	public  String getKeyByMerId(String merId) throws Exception{		
		String merKey=keyMap.get(merId);
		if(merKey!=null && merKey.trim().length()>1){			
			return merKey;
		}
		return null;
	}
	


}
