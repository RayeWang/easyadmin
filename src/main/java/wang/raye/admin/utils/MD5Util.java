package wang.raye.admin.utils;

import java.security.MessageDigest;

/**
 * md5加密的工具类
 * Created by Raye on 2016/11/29.
 */

public class MD5Util {
    public final static String MD5(String s) {
        byte[] btInput = s.getBytes();
        return MD5(btInput);
    }

    public final static String MD5(byte[] btInput) {
        char hexDigits[] = { '0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
