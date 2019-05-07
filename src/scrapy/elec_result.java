package scrapy;
import java.util.*;

public class elec_result {
	String name;
	int row,column;
	String week_form; // 0 represents odd days and 1 represents even days
	String classroom;
	String exam;
	
	elec_result(int row,int column, String name,String classroom,String week_form,String exam){
		this.name = name;
		this.row = row;
		this.classroom = classroom;
		this.column = column;
		this.week_form = week_form;
		this.exam = exam;
	}
}
