package manage.util;
//�ж�string�����Ƿ�Ϊ��ֵ
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
