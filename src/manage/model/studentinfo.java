package manage.model;

import java.util.Date;

public class studentinfo {
    
	private int stuId;
	private String stuNo;
	private String stuName;
	private String sex;
	private Date  brithday;
	private int classId=-1;
	private String email;
	private String stuDesc;
	
	
	
	public studentinfo() {
		
	}
	
	
	
	public studentinfo(String stuNo, String stuName, String sex, Date brithday, int classId, String email,
			String stuDesc) {
		super();
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.sex = sex;
		this.brithday = brithday;
		this.classId = classId;
		this.email = email;
		this.stuDesc = stuDesc;
	}



	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStuDesc() {
		return stuDesc;
	}
	public void setStuDesc(String stuDesc) {
		this.stuDesc = stuDesc;
	}
	
	
}
