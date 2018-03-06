package wang.raye.admin.utils;

/**
 * 加密相关工具类
 * Created by Raye on 2016/11/29.
 */

public class EncryptionUtils {
    public static String sign(String src,String timestamp){
        return MD5Util.MD5("wwzhiyezhilu"+StringUtils.sort(src)+timestamp);
    }
}
