package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class QueryController implements Initializable{

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
    
    private ObservableList<CourseProperty> data = FXCollections.observableArrayList();

    
    public void btnclicks(ActionEvent event) {
    	Stage stage = (Stage)btnback.getScene().getWindow();
    	if(event.getSource() == btnback) {
    		LoadScene(stage, "eleGUI.fxml");
    	}
    	else if(event.getSource() == btnquery) {
    		
    	}
    	else if(event.getSource() == btncourse) {
    		LoadScene(stage, "Courseinfo.fxml");
    	}
    	else if(event.getSource() == btnfavorite) {
    		LoadScene(stage, "Favoriteinfo.fxml");
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
    public void Loaddata(String query) {
    	data.clear();
    	//need query result
    	colcredit.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("credit"));
    	colname.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("name"));
    	coltea.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("teacher"));
    	coldepa.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("department"));
    	colinfo.setCellValueFactory(new PropertyValueFactory<CourseProperty,String>("info"));
    	
    	table.setItems(data);
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}