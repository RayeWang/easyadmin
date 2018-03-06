package wang.raye.admin.utils;

public class StringUtils {
	/**
	 * 对字符串进行排序
	 * @param str
	 * @return
     */
	public static String sort(String str){
		//利用toCharArray可将字符串转换为char型的数组
		char[] s1 = str.toCharArray();
		for(int i=0;i<s1.length;i++){
			for(int j=0;j<i;j++){
				if(s1[i]<s1[j]){
					char temp = s1[i];
					s1[i] = s1[j];
					s1[j] = temp;
				}
			}
		}
		//再次将字符数组转换为字符串，也可以直接利用String.valueOf(s1)转换
		String st = new String(s1);
		return st;
	}
	
	public static boolean isEmpty(String str){
		if(str == null || str.isEmpty()){
			return true;
		}
		return false;
	}
}
