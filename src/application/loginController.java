package application;

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

public class loginController implements Initializable{

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
    	if(event.getSource() == btnquit) {
    		System.exit(0);
    	}
    	else if (event.getSource() == btnoffli) {
    		try {
				Parent root = FXMLLoader.load(getClass().getResource("eleGUI.fxml"));
				Stage primaryStage = new Stage();
				primaryStage.setTitle("elective");
				primaryStage.setScene(new Scene(root,960,600));
				primaryStage.show();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
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
    			try {
    				Parent root = FXMLLoader.load(getClass().getResource("eleGUI.fxml"));
    				Stage primaryStage = new Stage();
    				primaryStage.setTitle("elective");
    				primaryStage.setScene(new Scene(root,960,600));
    				primaryStage.show();
    			}catch (Exception e) {
    				// TODO: handle exception
    				e.printStackTrace();
    			}
				//future.get();
    		}catch (Exception e) {
				// TODO: handle exception
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
