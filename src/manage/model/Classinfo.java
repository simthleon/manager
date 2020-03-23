package manage.model;

public class Classinfo {
    private int id;
    private String className;
    private String classDesc;
    
    
	public Classinfo(String className, String classDesc) {
		
		this.className = className;
		this.classDesc = classDesc;
	}
	public Classinfo() {
		super();
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassDesc() {
		return classDesc;
	}
	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}
    
    
	
}
