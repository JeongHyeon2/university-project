package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RadioButtonTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("RadiobuttonTest.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("폰트 색상 및 사이즈 변경");
		stage.show();
	}
	public static void main(String []args) {
		launch(args);
	}
	@FXML
    public RadioButton red,green,blue;
	@FXML
    public RadioButton font_10,font_11,font_12;
	@FXML
    public TextField text; 
	public void initialize() {
				ToggleGroup g1 = new ToggleGroup();
				ToggleGroup g2 = new ToggleGroup();
		font_10.setToggleGroup(g2);font_11.setToggleGroup(g2);font_12.setToggleGroup(g2);
    	red.setToggleGroup(g1);green.setToggleGroup(g1);blue.setToggleGroup(g1);
	}

     public void handle(ActionEvent event) {
    	if(red.isSelected()&&font_10.isSelected())text.setStyle("-fx-font: 10pt\"Arial\";-fx-text-fill: red;");
    	else if	(red.isSelected()&&font_11.isSelected())text.setStyle("-fx-font: 11pt\"Arial\";-fx-text-fill: red;");
    	else if(red.isSelected()&&font_12.isSelected())text.setStyle("-fx-font: 12pt\"Arial\";-fx-text-fill: red;");
    	else if(green.isSelected()&&font_10.isSelected())text.setStyle("-fx-font: 10pt\"Arial\";-fx-text-fill: green;");
    	else if(green.isSelected()&&font_11.isSelected())text.setStyle("-fx-font: 11pt\"Arial\";-fx-text-fill: green;");
    	else if(green.isSelected()&&font_12.isSelected())text.setStyle("-fx-font: 12pt\"Arial\";-fx-text-fill: green;");
    	else if(blue.isSelected()&&font_10.isSelected())text.setStyle("-fx-font: 10pt\"Arial\";-fx-text-fill: blue;");
    	else if(blue.isSelected()&&font_11.isSelected())text.setStyle("-fx-font: 11pt\"Arial\";-fx-text-fill: blue;");
    	else if(blue.isSelected()&&font_12.isSelected())text.setStyle("-fx-font: 12pt\"Arial\";-fx-text-fill: blue;");
    }
}

