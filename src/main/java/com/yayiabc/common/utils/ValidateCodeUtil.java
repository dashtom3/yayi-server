package com.yayiabc.common.utils;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;





public class ValidateCodeUtil {
	public static Map<String, String> codeMap =new  HashMap<String, String>();
	public static String getCode() {
			String validateCode="";
			do{	
				validateCode = getRandom();
				System.out.println(validateCode);
			}while(codeMap.containsValue(validateCode));
			//判断生成的验证码是否存在，不存在则写到map
			codeMap.put(validateCode,validateCode);
			return validateCode;
	}
    


     private static String getRandom() {
         StringBuffer randomCodeRes = new StringBuffer();
         char[] codeSequenceChar = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                 'X', 'Y', 'Z'
         };
         // 创建一个随机数生成器类
         Random random = new Random();
         //随机产生，验证码由几个数字、几个字母组成
         int charNum  =8;
         for (int i = 0; i < charNum; i++) {
             // 得到随机产生的验证码字母。
             String strRand = String.valueOf(codeSequenceChar[random.nextInt(codeSequenceChar.length)]);
             // 将产生的六个随机数组合在一起。
             randomCodeRes.append(strRand);
         }
         return randomCodeRes.toString();
     } 
}
