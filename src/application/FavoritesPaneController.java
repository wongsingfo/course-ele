package application;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FavoritesPaneController extends Controller implements Initializable{
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

    @FXML
    private TableView<CourseProperty> table;
    
    @FXML
    private TableColumn<CourseProperty, String> colname;

    @FXML
    private TableColumn<CourseProperty, String> colinfo;

    @FXML
    private TableColumn<CourseProperty, String> colcredit;
    
    @FXML
    private TableColumn<CourseProperty, String> coldepa;
    
    @FXML
    private TableColumn<CourseProperty, String> coltea;
    
    @FXML
    private TableColumn<CourseProperty, String> favorites;
        
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
    		// replaceSceneContent(stage, "fxml-favorite.fxml");
    	}
    	else if(event.getSource() == btntimetable) {
    		replaceSceneContent(stage, "fxml-timetable.fxml");
    	}
    }
    
    public static void writeFavoriteCsv(String path) throws IOException {
    	// TODO write file
    	BufferedWriter writer = new BufferedWriter(new FileWriter(path));
    	for (CourseProperty courses:App.savedFavorites) {
    		writer.write(courses.getName()+","+courses.getCredit()+","+courses.getTeacher()
    					+","+courses.getDepartment()+","+courses.getInfo().replace("\n", "<br />"));
    		writer.newLine();
    	}
    	writer.flush();
    	writer.close();
    }
	
    public static boolean isContains(CourseProperty c) {
		// 存在课程名、教师、院系均相等的情况
    	String courseInfo = c.getInfo(); 
		for (CourseProperty courses: App.savedFavorites) {
			if ( courseInfo.equals(courses.getInfo()) ) {
				return true;
			}
		}
		
		return false;
	}
    
    private void loaddata(String path) {
    	if (App.isFirstAccessFlag) {
    		App.isFirstAccessFlag = false;
    		try {
    			BufferedReader reader = new BufferedReader(new FileReader(path));
    			String line = null;
    			while((line = reader.readLine()) != null){
    				String[] item =  line.split(",");
    				App.savedFavorites.add(new CourseProperty(item[0], item[1], item[2], item[3],item[4].replace("<br />", "\n")));
    			
    			}
    		}catch (FileNotFoundException e) {
    			// Do nothing
    		
    		}catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	
		Callback<TableColumn<CourseProperty,String> ,TableCell<CourseProperty,String>> cellFactory = 
	    		new Callback<TableColumn<CourseProperty,String> ,TableCell<CourseProperty,String>>() {
	    	
	    		@Override
	    		public TableCell<CourseProperty, String> call(final TableColumn<CourseProperty, String> param) {
	    			final TableCell<CourseProperty, String> cell = new TableCell<CourseProperty, String>() {
	    				final Button btn = new Button(" 取 消 收 藏 ");
	    				
	    				@Override
	    				public void updateItem(String item, boolean empty) {
	    					super.updateItem(item, empty);
	    					if (empty) {
	    						setGraphic(null);
	    						setText(null);
	    					}
	    					else {
	    						btn.setOnAction(event-> {
	    							CourseProperty c = getTableView().getItems().get(getIndex());
	    							Alert inform;
	    			    			inform = new Alert(AlertType.INFORMATION,"课程“"+c.getName()+"”已取消收藏！");
	    			    			inform.setHeaderText("");
	    			    			inform.showAndWait();
	    			    			App.savedFavorites.remove(c);
	    			    			
	    						});
	    						setGraphic(btn);
	    						setText(null);
	    					}
	    				}
	    			};
	    			return cell;
	   			}
	   		};
	    	
	    	colcredit.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("credit"));
	    	colname.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("name"));
	    	coltea.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("teacher"));
	    	coldepa.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("department"));
	    	colinfo.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("info"));
	    	favorites.setCellFactory(cellFactory);
	    		
	    	table.setItems(App.savedFavorites);
		
	}
    
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// TODO 显示表格框架
    		loaddata("data/favorites.csv");
    	
    }

}