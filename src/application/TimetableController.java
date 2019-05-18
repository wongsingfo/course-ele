package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import query.Class;
import query.CourseQuery;
import query.Interval;

public class TimetableController extends Controller {
	
	@FXML
    private Button btnback;

    @FXML
    private Button btnfavorite;
   
    @FXML
    private Button btnquery;

    @FXML
    private Button btntimetable;

    @FXML
    private Button btncourse;
    
    
    
    public void btn_Clicks(ActionEvent event) {
    	Stage stage = (Stage)btnback.getScene().getWindow();
    	if(event.getSource() == btnback) {
    		replaceSceneContent(stage, "fxml-dashboard.fxml");
    	}
    	else if(event.getSource() == btnquery) {
    		replaceSceneContent(stage, "fxml-query.fxml");
    	}
    	else if(event.getSource() == btncourse) {
    		replaceSceneContent(stage, "fxml-courseinfo.fxml");
    	}
    	else if(event.getSource() == btnfavorite) {
    		replaceSceneContent(stage, "fxml-favorite.fxml");
    	}
    	else if(event.getSource() == btntimetable) {
    		replaceSceneContent(stage, "fxml-timetable.fxml");
    	}
    }
	
	public class span{
		int from,to;
		public span(int a,int b) {
			from=a;
			to=b;
		}
	}
	public class CourseDay{
		String week,day;
		public CourseDay(String w,String d) {
			week=w;day=d;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((day == null) ? 0 : day.hashCode());
			result = prime * result + ((week == null) ? 0 : week.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CourseDay other = (CourseDay) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (day == null) {
				if (other.day != null)
					return false;
			} else if (!day.equals(other.day))
				return false;
			if (week == null) {
				if (other.week != null)
					return false;
			} else if (!week.equals(other.week))
				return false;
			return true;
		}
		private TimetableController getEnclosingInstance() {
			return TimetableController.this;
		}
		
	}
	public class CourseTime{
		CourseDay cday;
		String tclass;
		public CourseTime(String w,String d,String t) {
			cday = new CourseDay(w, d);
			tclass=t;
		}
	}
	public class Course{
		String name,classroom,other_info;
		List<CourseTime> time=new ArrayList<CourseTime>();
	
		public Course(CourseTime ct,String n,String cr,String oi) {
			name=n;classroom=cr;other_info=oi;
			time.add(ct);
		}
	}
	
	private final int prefWidth = 122;
	private final int prefHeight = 40;
	private final double paddingWidth = 10.0;
	private final String[] colorScheme = {"#F8C3CD", "#FEDFE1"};
	
	@FXML
	private GridPane table;
	
	private void addBlock(Interval interval, String content) {
		AnchorPane block = new AnchorPane();
		block.getStyleClass().add("course-block");
		
		GridPane.setRowIndex(block, interval.getFrom()); 
		GridPane.setColumnIndex(block, interval.getDay().getValue());
		GridPane.setRowSpan(block, interval.getSpan());
		
		block.setPrefWidth(prefWidth);
		block.setPrefHeight(prefHeight * interval.getSpan());
		table.getChildren().add(block);
		
		Label label = new Label(content);
		label.setMaxWidth(prefWidth - paddingWidth * 2);
		label.setMaxHeight(prefHeight * interval.getSpan() - paddingWidth * 2);
		label.setWrapText(true);
		AnchorPane.setTopAnchor(label, paddingWidth);
		AnchorPane.setLeftAnchor(label, paddingWidth);
		block.getChildren().add(label);
	}
	
	private void lalala(int x, int y, String content) {
		AnchorPane block = new AnchorPane();
		block.getStyleClass().add("course-block");
		
		GridPane.setRowIndex(block, x); 
		GridPane.setColumnIndex(block, y);
		
		block.setPrefWidth(prefWidth);
		block.setPrefHeight(prefHeight);
		table.getChildren().add(block);
		
		Label label = new Label(content);
		label.setMaxWidth(prefWidth - paddingWidth * 2);
		AnchorPane.setTopAnchor(label, paddingWidth);
		AnchorPane.setLeftAnchor(label, paddingWidth);
		block.getChildren().add(label);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		table.getStyleClass().add("table-grid");
		
		table.getColumnConstraints().add(new ColumnConstraints(30));
		for (int i = 1; i <= 7; i++) {
			table.getColumnConstraints().add(new ColumnConstraints(prefWidth));
		}
		for (int i = 0; i <= 12; i++) {
			table.getRowConstraints().add(new RowConstraints(prefHeight));
		}
		
	
		
		Map<String,Course> courses=new HashMap<String,Course>();
		try {
			
			BufferedReader reader=new BufferedReader(new FileReader("data/ele_result.csv"));
			String line="";
			reader.readLine();
			reader.readLine();
			while((line=reader.readLine())!=null) {
				if(!line.isBlank()) {
					
					String[] s=line.split(",");
					Course temp=courses.get(s[2]);
					String w="单";
					CourseTime t=new CourseTime(w,s[1],s[0]);
					if(courses.containsKey(s[2])){		
						temp.time.add(t);
					}
					else {
						Course newcourse=new Course(t,s[2],s[3],s[4]);
						courses.put(s[2], newcourse);
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Course c:courses.values()) {
			Map<CourseDay,span> rec=new HashMap<CourseDay,span>();
			for(CourseTime ct:c.time) {
				if(rec.containsKey(ct.cday)) {
					int current=Integer.parseInt(ct.tclass);
					
					if(current==rec.get(ct.cday).from -1) {
						rec.get(ct.cday).from=current;
					}
					else if(current==rec.get(ct.cday).to+1) {
						rec.get(ct.cday).to=current;
					}
				}
				else {
					span ts=new span(Integer.parseInt(ct.tclass),Integer.parseInt(ct.tclass));
					rec.put(ct.cday,ts);
				}
				
			}
	//		addBlock(new Interval("单"，"一"))
			
			for(Map.Entry<CourseDay,span> oneday : rec.entrySet()) {
				CourseDay tkey=oneday.getKey();
				span tvalue=oneday.getValue();
				addBlock(new Interval(tkey.week,tkey.day,Integer.toString(tvalue.from),Integer.toString(tvalue.to)),
						c.name+"\n"+c.classroom+"\n"+c.other_info);
			}
			
		}
		//addBlock(new Interval("单"，""))
		for(int i=1;i<=12;i++) {
			lalala(i,0,Integer.toString(i));
		}
		lalala(0,1,"周一");
		lalala(0,2,"周二");
		lalala(0,3,"周三");
		lalala(0,4,"周四");
		lalala(0,5,"周五");
		lalala(0,6,"周六");
		lalala(0,7,"周日");
	}

}