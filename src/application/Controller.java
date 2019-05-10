package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Controller implements Initializable {

	/**
	 * 打开一个新的stage
	 * @param fxml fxml文件路径
	 */
	void createStage(String fxml) {
		Stage stage = new Stage();
		try {
			Parent page = FXMLLoader.load(getClass().getResource(fxml));
			stage.setTitle("elective");
			stage.setScene(new Scene(page));
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		stage.sizeToScene();
		stage.show();
	}
	
	/**
	 * 移进到一个新的stage，即：
	 * <p>
	 * 关闭当前stage，打开新stage
	 * <p>
	 * replaceSceneContent() 可以达到类似效果！
	 * 
	 * @param currentStage 当前的stage
	 * @param fxml fxml文件路径
	 * 
	 * @see replaceSceneContent
	 */
	void nextStage(Stage currentStage, String fxml) {
		currentStage.close();
		createStage(fxml);
	}
	
	/**
	 * 变换stage的scene
	 * @param stage 当前的stage
	 * @param fxml fxml文件路径
	 */
	void replaceSceneContent(Stage stage, String fxml) {
        Parent page = null;
		try {
			page = FXMLLoader.load(getClass().getResource(fxml));	
//			page = (Parent) FXMLLoader.load(App.class.getResource(fxml), null, new JavaFXBuilderFactory());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        // return page;
    }
}
