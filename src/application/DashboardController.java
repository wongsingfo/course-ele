package application;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import query.*;
import query.Class;

public class DashboardController extends Controller implements Initializable{

    @FXML
    private Button btncalendar;

    @FXML
    private Button btnfavorite;

    @FXML
    private Button btncourses;
    
    @FXML			
    private Button btnquery;

    
    public void btnclick(ActionEvent mouseEvent) {
    	Stage stage = (Stage)btnquery.getScene().getWindow();
    	
		if(mouseEvent.getSource() == btncalendar) {
			replaceSceneContent(stage, "fxml-timetable.fxml");
		}
		else if (mouseEvent.getSource() == btnfavorite) {
			replaceSceneContent(stage, "fxml-favorite.fxml");
		}
		else if(mouseEvent.getSource() == btncourses) {
			replaceSceneContent(stage, "fxml-courseinfo.fxml");
		}
		else if(mouseEvent.getSource() == btnquery) {
			replaceSceneContent(stage, "fxml-query.fxml");
			
		}
	}
    private CourseQuery cq = null;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 
	}
	
}

