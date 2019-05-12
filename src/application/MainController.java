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

public class MainController implements Initializable{

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
		if(mouseEvent.getSource() == btncalendar) {
			//new FXMLLoader(getClass().getResource(""));
		}
		else if (mouseEvent.getSource() == btnfavorite) {
			//new FXMLLoader(getClass().getResource(""));
		}
		else if(mouseEvent.getSource() == btncourses) {
			Stage stage = (Stage)btnquery.getScene().getWindow();
			try {
				stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Courseinfo.fxml")),960,600));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(mouseEvent.getSource() == btnquery) {
			String text = txtquery.getText();
			//CourseQuery.test(text);
			Stage stage = (Stage)btnquery.getScene().getWindow();
			try {
				stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Queryinfo.fxml")),960,600));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 
	}
	private void LoadStages(String fxml) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(fxml));
			Stage primaryStage = new Stage();
			primaryStage.setTitle("elective");
			primaryStage.setScene(new Scene(root,960,600));
			primaryStage.show();
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
