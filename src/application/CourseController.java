package application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import query.*;
import application.FavoritesPaneController;


public class CourseController extends Controller implements Initializable{

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
    private TableColumn<CourseProperty, String> saveToFavorite;
    
    
    @FXML
    private ChoiceBox<String> choicetype;
    
    private ObservableList<CourseProperty> data = FXCollections.observableArrayList();
    
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
    
    public void loaddata(String filename) {
    	data.clear();
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader(filename));
    		reader.readLine();
    		reader.readLine();
    		String line = null;
    		while((line = reader.readLine()) != null){
    			String[] item =  line.split(",");
    			if(item.length > 9) {
    				data.add(new CourseProperty(item[1], item[3], item[4], item[6],item[9].replace("<br />", "\n")));
    			}
    		}
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	
    	Callback<TableColumn<CourseProperty,String> ,TableCell<CourseProperty,String>> cellFactory = 
    		new Callback<TableColumn<CourseProperty,String> ,TableCell<CourseProperty,String>>() {
    	
    		@Override
    		public TableCell<CourseProperty, String> call(final TableColumn<CourseProperty, String> param) {
    			final TableCell<CourseProperty, String> cell = new TableCell<CourseProperty, String>() {
    				final Button btn = new Button(" 点 击 收 藏 ");
    				
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
    			    			if (FavoritesPaneController.isContains(c)) {
    			    				inform = new Alert(AlertType.INFORMATION,"课程“"+c.getName()+"”已收藏，请勿重复操作。");
    			    			}
    			    			else {
    			    				inform = new Alert(AlertType.INFORMATION,"课程“"+c.getName()+"”收藏成功！么么哒～");
    			    				App.savedFavorites.add(c);
    			    			}
    			    			inform.setHeaderText("");
    			    			inform.showAndWait();
    			    			
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
    	saveToFavorite.setCellFactory(cellFactory);
    		
    	table.setItems(data);
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	ObservableList<String> choice = FXCollections.observableArrayList
    			("培养计划","专业课","政治课","英语课","体育课","通选课","公选课","文计");
    	choicetype.setItems(choice);
    	choicetype.setValue("培养计划");
    	loaddata("data/ele_plan_data.csv");
    	choicetype.getSelectionModel().selectedItemProperty().
    	addListener((ObservableValue<? extends String> value,String oldone,String newone)->{
    		if(newone == "培养计划") {
    			loaddata("data/ele_plan_data.csv");
    		}
    		else if(newone == "专业课") {
    			loaddata("data/speciality_data.csv");
    		}
    		else if(newone == "政治课") {
    			loaddata("data/politics_data.csv");
    		}
    		else if(newone == "体育课") {
    			loaddata("data/gym_data.csv");
    		}
    		else if (newone == "英语课") {
    			loaddata("data/english_data.csv");
    		}
    		else if(newone == "通选课") {
    			loaddata("data/trans_choice_data.csv");
    		}
    		else if(newone == "公选课") {
    			loaddata("data/pub_choice_data.csv");
    		}
    		else if(newone == "文计") {
    			loaddata("data/liberal_computer_data.csv");
    		}
    	});
    }
}
