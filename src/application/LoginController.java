package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import scrapy.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import application.FavoritesPaneController;

public class LoginController extends Controller implements Initializable{

    @FXML
    private TextField txtid;

    @FXML
    private ImageView btnenter;

    @FXML
    private Button btnquit;
    
    @FXML
    private ProgressIndicator prolog;
    
    @FXML
    private Button btnlogin;
    
    @FXML
    private Button btnoffli;
    
    @FXML
    private PasswordField txtpass;
    
    public void btnclicks(ActionEvent event) {
    	Object src = event.getSource();
    	
    	Stage stage = (Stage) btnlogin.getScene().getWindow();
    	
    	if(src == btnquit) {
    		try {
				FavoritesPaneController.writeFavoriteCsv("data/favorites.csv");
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    	    stage.close();
    	    System.exit(0);
    	}
    	else if (event.getSource() == btnoffli) {
    		createStage("fxml-dashboard.fxml");
    	}
    	else if (event.getSource() == btnlogin) {
    		btnlogin.setVisible(false);
			prolog.setVisible(true);
    		try {
    			prolog.setVisible(true);
            	String id = txtid.getText();
        		String password = txtpass.getText();
    	    	Scrapy_elective.getData(id, password);
    	    	prolog.setVisible(false);
    			btnlogin.setVisible(true);
    			nextStage(stage, "fxml-dashboard.fxml");
    		}catch (Exception e) {
    			Alert error = new Alert(AlertType.ERROR,"用户名或密码错误\n请重新输入");
    			error.setHeaderText("login error");
    			error.showAndWait();
    			prolog.setVisible(false);
    			btnlogin.setVisible(true);
    		}
    	}
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
