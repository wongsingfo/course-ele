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
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import query.*;


public class CourseController implements Initializable{

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
    private TextField txtquery;

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
    private ChoiceBox<String> choicetype;
    
    private ObservableList<CourseProperty> data = FXCollections.observableArrayList();

    public void btn_Clicks(ActionEvent event) {
    	Stage stage = (Stage)btnback.getScene().getWindow();
    	if(event.getSource() == btnback) {
    		LoadScene(stage, "eleGUI.fxml");
    	}
    	else if(event.getSource() == btnquery) {
    		LoadScene(stage, "Queryinfo.fxml");
    	}
    	else if(event.getSource() == btncourse) {
    		LoadScene(stage, "Courseinfo.fxml");
    	}
    	else if(event.getSource() == btnfavorite) {
    		//LoadScene(stage, "Favoriteinfo.fxml");
    	}
    	else if(event.getSource() == btntimetable) {
    		LoadScene(stage, "Timetable.fxml");
    	}
    }
    public void LoadScene(Stage stage,String fxml) {
    	try {
    		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxml)),960,600));
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }
    
    public void Loaddata(String filename) {
    	data.clear();
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader(filename));
    		reader.readLine();
    		reader.readLine();
    		String line = null;
    		while((line = reader.readLine()) != null){
    			String[] item =  line.split(",");
    			if(item.length > 9)data.add(new CourseProperty(item[1], item[3], item[4], item[6],item[9].replace("<br />", "\n")));
    		}
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	colcredit.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("credit"));
    	colname.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("name"));
    	coltea.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("teacher"));
    	coldepa.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("department"));
    	colinfo.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("info"));
    	
    	table.setItems(data);
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	ObservableList<String> choice = FXCollections.observableArrayList
    			("培养计划","专业课","政治课","英语课","体育课","通选课","公选课","文计");
    	choicetype.setItems(choice);
    	choicetype.setValue("培养计划");
    	Loaddata("data/ele_plan_data.csv");
    	choicetype.getSelectionModel().selectedItemProperty().
    	addListener((ObservableValue<? extends String> value,String oldone,String newone)->{
    		if(newone == "培养计划") {
    			Loaddata("data/ele_plan_data.csv");
    		}
    		else if(newone == "专业课") {
    			Loaddata("data/speciality_data.csv");
    		}
    		else if(newone == "政治课") {
    			Loaddata("data/politics_data.csv");
    		}
    		else if(newone == "体育课") {
    			Loaddata("data/gym_data.csv");
    		}
    		else if (newone == "英语课") {
    			Loaddata("data/english_data.csv");
    		}
    		else if(newone == "通选课") {
    			Loaddata("data/trans_choice_data.csv");
    		}
    		else if(newone == "公选课") {
    			Loaddata("data/pub_choice_data.csv");
    		}
    		else if(newone == "文计") {
    			Loaddata("data/liberal_computer_data.csv");
    		}
    	});
    }
}
