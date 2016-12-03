package com.rc.openapi.util.sign;

public class SignConfig {

	// 加密方式
	public final static String sign_type = "MD5";
	// 商户秘钥
	public final static String key = "商户秘钥";
	// 编码格式
	public final static String input_charset = "utf-8";
	// 商户id的参数名称
	public final static String partner_keyname = "parnterid";
	// 签名的参数名称
	public final static String key_keyname = "sign";
	// 签名类型的参数名称
	public final static String sign_type_keyname = "sign_type";
}
