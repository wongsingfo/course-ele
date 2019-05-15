package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import query.Interval;

public class TimetableController extends Controller {
	
	private final int prefWidth = 119;
	private final int prefHeight = 34;
	private final double paddingWidth = 10.0;
	private final String[] colorScheme = {"#F8C3CD", "#FEDFE1"};
	
	@FXML
	private GridPane table;
	
	private void addBlock(Interval interval, String content) {
		AnchorPane block = new AnchorPane();
		block.getStyleClass().add("course-block");
		
		GridPane.setRowIndex(block, interval.getFrom()); 
		GridPane.setColumnIndex(block, interval.getDay().getValue());
		GridPane.setRowSpan(block, interval.getSpan());
		
		block.setPrefWidth(prefWidth);
		block.setPrefHeight(prefHeight * interval.getSpan());
		table.getChildren().add(block);
		
		Label label = new Label(content);
		label.setMaxWidth(prefWidth - paddingWidth * 2);
		AnchorPane.setTopAnchor(label, paddingWidth);
		AnchorPane.setLeftAnchor(label, paddingWidth);
		block.getChildren().add(label);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		table.getStyleClass().add("table-grid");
		
		table.getColumnConstraints().add(new ColumnConstraints(30));
		for (int i = 1; i <= 7; i++) {
			table.getColumnConstraints().add(new ColumnConstraints(prefWidth));
		}
		for (int i = 0; i <= 12; i++) {
			table.getRowConstraints().add(new RowConstraints(prefHeight));
		}

		addBlock(new Interval("全都要", "一", "1", "3"), "啦啦啦\n在二教");
		addBlock(new Interval("双", "三", "1", "2"), "早课我好长长长长长长长长长长长长长长长长");
		addBlock(new Interval("单", "日", "1", "12"), "一整天");
		
	}

}