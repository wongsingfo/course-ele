package application;

//import java.lang.ModuleLayer.Controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

/**
 * JavaFX基本理解：
 * <p>
 * 一个窗口是一个Stage，窗口的内容是Scene
 * <p>
 * 一个fxml文件描述一个Scene，并且关联了一个Controller
 * <p>
 * Controller响应用户的操作
 * 
 * 
 *
 */
public class App extends Application {
	private double xoffset = 0;
	private double yoffset = 0;
 	@Override
	public void start(Stage primaryStage)throws Exception {
 		FXMLLoader fxml = new FXMLLoader(getClass().getResource("fxml-login.fxml"));
		Parent root = fxml.load();
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setTitle("elective");
		
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xoffset = event.getSceneX();
				yoffset = event.getSceneY();
			}
		});
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() - xoffset);
				primaryStage.setY(event.getScreenY() - yoffset);
			}
		});
		Scene scene = new Scene(root);
		//Controller controller = fxml.getController();
		scene.setFill(Color.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
