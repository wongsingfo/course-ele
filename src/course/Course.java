package course;

/**
 * 一个关于课程的类，一门课包含如下信息
 * <br>
 *  课程号 课程名 课程类别 学分 周学时 教师 班号 开课单位 年级 上课时间/考试信息
 *
 */
public class Course {
	
	public Course(String id, String name, String type, String credit, String hours, String instructor, String id2,
			String department, String grade, String comment) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.credit = credit;
		this.hours = hours;
		this.instructor = instructor;
		this.id2 = id2;
		this.department = department;
		this.grade = grade;
		this.comment = comment;
		
		properties = new String[] {
			id, 
			name, 
			type, 
			credit, 
			hours, 
			instructor, 
			id2,
			department, 
			grade,
			comment
		};
	}
	
	/**
	 * 未完成
	 */
	public Course(String[] properties) {
		this.properties = properties;
		/* unfinished */
	}

	/**
	 * 课程属性
	 */
	private String 
		id, 
		name, 
		type, 
		credit, 
		hours, 
		instructor, 
		id2,
		department, 
		grade,
		comment;
	
	private String[] properties;
	
	/**
	 * 返回第 {@code i}  个课程属性 
	 * @param i  (Zero-based) 
	 * @return 描述课程属性的字符串
	 */
	public String getProperty(int i) {
		return properties[i];
	}
}
