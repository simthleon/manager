package manage.util;
//判断string类型是否为空值
public class StringUtil {
    
	public static boolean isEmpty(String str) {
		if(str.equals("")||str==null) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str) {
		if("".equals(str)||str==null) {
			return false;
		}else {
			return true;
		}
	}
	
	
}
