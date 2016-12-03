package com.rc.openapi.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 15:23
 */
public class Signature {

	

    public static String getWXSign(Map<String,String> map, String key )
    {
        return getSign( map, key , "&key=", true );
    }
    public static String getSign(Map<String,String> map, String key )
    {
        return getSign( map, key , "", false );
    }
    public static String getSign(Map<String,String> map, String key, boolean toUpperCase )
    {
        return getSign( map, key , "", toUpperCase );
    }
	
	public static String getSign(Map<String,String> map, String key, String keyPrefix, boolean toUpperCase )
    {
        ArrayList<String> list = new ArrayList<String>();

        for( Map.Entry<String,String> entry : map.entrySet() )
        {
            if( "".equals( entry.getValue() )  )
            {
                continue;
            }
            String item = "";
            item += entry.getKey() + "=" + entry.getValue();
            list.add( item );
        }

        int size = list.size();
        String [] arrayToSort = list.toArray( new String[ size ] );

        Arrays.sort( arrayToSort, String.CASE_INSENSITIVE_ORDER );

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++)
        {
            if( i > 0 )
            {
                sb.append( "&" );
            }
            sb.append(arrayToSort[i]);
        }
        sb.append( keyPrefix );
        sb.append(key);

        String result = sb.toString();
        System.out.println("result===========>"+result);
        result = DigestUtils.md5Hex(result);
        if( toUpperCase )
        {
            result = result.toUpperCase();
        }

        return result;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	  public static String getSign(Map<String,String> map){
	        ArrayList<String> list = new ArrayList<String>();
	        for(Map.Entry<String,String> entry:map.entrySet()){
	            if(entry.getValue()!=""){
	                list.add(entry.getKey() + "=" + entry.getValue() + "&");
	            }
	        }
	        int size = list.size();
	        String [] arrayToSort = list.toArray(new String[size]);
	        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
	        StringBuilder sb = new StringBuilder();
	        for(int i = 0; i < size; i ++) {
	            sb.append(arrayToSort[i]);
	        }
	        String original = sb.toString();
	        original += "key=" + InfoUtil.getInstance().getInfo("config", "wxPaymentPk");
//	        Util.log("Sign Before MD5:" + original);
//	        String result = MD5.MD5Encode( original ).toUpperCase();
//	        Util.log("Sign Result:" + result);
	        String result = DigestUtils.md5Hex( original ).toUpperCase();
//	        Util.log( "Sign Result(Digest):" + result );

	        return result;

	    }



}
