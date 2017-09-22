package com.yayiabc.common.utils;


import java.util.UUID;

public class ValidateCodeUtil {
//	public static Map<String, String> codeMap =new HashMap<String, String>();
//	public static String getCode() {
//			String validateCode="";
//			do{
//				validateCode = getRandom();
//				System.out.println(validateCode);
//			}while(codeMap.containsValue(validateCode));
//			//判断生成的验证码是否存在，不存在则写到map
//			codeMap.put(validateCode,validateCode);
//			return validateCode;
//	}
//
//
//
//     private static String getRandom() {
//         StringBuffer randomCodeRes = new StringBuffer();
//         char[] codeSequenceChar = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
//                 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
//                 'X', 'Y', 'Z'
//         };
//         // 创建一个随机数生成器类
//         Random random = new Random();
//         //随机产生，验证码由几个数字、几个字母组成
//         int charNum  =8;
//         for (int i = 0; i < charNum; i++) {
//             // 得到随机产生的验证码字母。
//             String strRand = String.valueOf(codeSequenceChar[random.nextInt(codeSequenceChar.length)]);
//             // 将产生的六个随机数组合在一起。
//             randomCodeRes.append(strRand);
//         }
//         return randomCodeRes.toString();
//     }

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };


    public static String getCode(){
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }



}
