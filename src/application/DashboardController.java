package application;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import query.*;

public class DashboardController extends Controller implements Initializable{

    @FXML
    private Button btncalendar;

    @FXML
    private Button btnfavorite;

    @FXML
    private Button btncourses;
    
    @FXML			
    private Button btnquery;

    @FXML
    private TextField txtquery;
    
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
			String text = txtquery.getText();
			//CourseQuery.test(text);
			replaceSceneContent(stage, "fxml-query.fxml");
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 
	}
	
}

