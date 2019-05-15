package application;

import javafx.beans.property.SimpleStringProperty;

public class CourseProperty {
	private SimpleStringProperty name;
	private SimpleStringProperty credit;
	private SimpleStringProperty teacher;
	private SimpleStringProperty department;
	private SimpleStringProperty info;
	public CourseProperty(String name,String credit,String teacher,String department,String info) {
		this.name = new SimpleStringProperty(name);
		this.credit = new SimpleStringProperty(credit);
		this.department = new SimpleStringProperty(department);
		this.teacher = new SimpleStringProperty(teacher);
		this.info = new SimpleStringProperty(info);
	}
	public void setName(String name) {this.name = new SimpleStringProperty(name);}
	public String getName() {
		return name.get();
	}
	public void setCredit(String credit) {this.credit = new SimpleStringProperty(credit);}
	public String getCredit() {
		return credit.get();
	}
	public void setDepartment(String department) {this.department = new SimpleStringProperty(department);}
	public String getDepartment() {
		return department.get();
	}
	public void setTeacher(String teacher) {this.teacher = new SimpleStringProperty(teacher);}
	public String getTeacher() {
		return teacher.get();
	}
	public void setInfo(String info) {this.info = new SimpleStringProperty(info);}
	public String getInfo() {
		return info.get();
	}
	
}
