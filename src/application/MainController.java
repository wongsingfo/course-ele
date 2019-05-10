package application;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController extends Controller implements Initializable{

    @FXML
    private Button btncalendar;

    @FXML
    private Button btnfavorite;

    @FXML
    private Button btncourses;

    @FXML
    private TextField txtquery;
    
    public void btnclick(ActionEvent mouseEvent) {
		if(mouseEvent.getSource() == btncalendar) {
			//new FXMLLoader(getClass().getResource(""));
		}
		else if (mouseEvent.getSource() == btnfavorite) {
			//new FXMLLoader(getClass().getResource(""));
		}
		else if(mouseEvent.getSource() == btncourses) {
			createStage("courseinfo.fxml");
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}

