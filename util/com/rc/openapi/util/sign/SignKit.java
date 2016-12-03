package com.rc.openapi.util.sign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class SignKit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 根据请求参数Map，生成签名
	 * 
	 * @param sParaTemp
	 *            请求前的参数数组
	 * @return 要请求的参数数组
	 */
	private static String buildRequestSign(Map<String, String> sParaTemp) {
		// 除去数组中的空值和签名参数
		Map<String, String> sPara = SignKit.paraFilter(sParaTemp);
		// 生成签名结果
		String mysign = buildRequestMysign(sPara);

		return mysign;
	}

	/**
	 * 生成要请求给支付宝的参数数组
	 * 
	 * @param sParaTemp
	 *            请求前的参数数组
	 * @return 要请求的参数数组
	 */
	private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
		// 除去数组中的空值和签名参数
		Map<String, String> sPara = SignKit.paraFilter(sParaTemp);
		// 生成签名结果
		String mysign = buildRequestMysign(sPara);

		// 签名结果与签名方式加入请求提交参数组中
		sPara.put("sign", mysign);
		sPara.put("sign_type", SignConfig.sign_type);

		return sPara;
	}

	/**
	 * 除去数组中的空值和签名参数
	 * 
	 * @param sArray
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map<String, String> paraFilter(Map<String, String> sArray) {

		Map<String, String> result = new HashMap<String, String>();

		if (sArray == null || sArray.size() <= 0) {
			return result;
		}

		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase(SignConfig.key_keyname) || key.equalsIgnoreCase(SignConfig.sign_type_keyname)) {
				continue;
			}
			result.put(key, value);
		}

		return result;
	}

	/**
	 * 生成签名结果
	 * 
	 * @param sPara
	 *            要签名的数组
	 * @return 签名结果字符串
	 */
	public static String buildRequestMysign(Map<String, String> sPara) {
		String prestr = SignKit.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String mysign = "";
		if (SignConfig.sign_type.equals("MD5")) {
			mysign = MD5.sign(prestr, SignConfig.key, SignConfig.input_charset);
		}
		return mysign;
	}

	
	/**
	 * 传入秘钥，生成签名结果
	 * 
	 * @param sPara
	 *            要签名的数组
	 * @return 签名结果字符串
	 */
	public static String buildSignByKey(Map<String, String> sPara,String key) {
		String prestr = SignKit.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String mysign = "";
		if (SignConfig.sign_type.equals("MD5")) {
			mysign = MD5.sign(prestr, key, SignConfig.input_charset);
		}
		return mysign;
	}	
	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

	/**
	 *  根据请求，把请求里的参数全部都取出来，放到一个Map里
	 * @param request
	 * @return  Map<String, String>
	 */
	public static Map<String, String> getRequestMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			String value = request.getParameter(paraName);
			map.put(paraName, value);
			System.out.println(paraName + ": " + request.getParameter(paraName));
		}
		return map;
	}
	
	
	/**
	 * 根据参数Map，验证签名，判断签名是否正确
	 * @param map
	 * @return
	 */
	public static boolean verifySign(Map<String, String> map,String sign){
		// 除去数组中的空值和签名参数
		Map<String, String> mapPara=SignKit.paraFilter(map);
		// 生成签名结果
		String mysign = buildSignByKey(mapPara,sign);
		String req_sign=map.get("sign");
		boolean f=false;
		if(mysign.equals(req_sign)){
			f=true;
		}
		return f;
	}
}
