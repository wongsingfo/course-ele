package application;

import java.net.URL;
import java.util.ResourceBundle;
import scrapy.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController extends Controller implements Initializable{

    @FXML
    private TextField txtid;

    @FXML
    private ImageView btnenter;

    @FXML
    private Button btnquit;

    @FXML
    private Button btnlogin;
    
    @FXML
    private Button btnskip;

    @FXML
    private PasswordField txtpass;
    
    public void btnclicks(ActionEvent event) {
    	Object src = event.getSource();
    	
    	Stage stage = (Stage) btnlogin.getScene().getWindow();
    	
    	if(src == btnquit) {
    	    stage.close();
    	}
    	else if (src == btnskip) {
    		replaceSceneContent(stage, "eleGUI.fxml");
    	}
//    	else if (event.getSource() == btnlogin) {
//    		String id = txtid.getText();
//    		String password = txtpass.getText();
//    		WebDriver driver = Scrapy_elective.log_in(id, password);
//    		return driver;
//    	}
//    	return new ChromeDriver();
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
