package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class TapPaneTest extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("TapPaneTest.fxml"));
		Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TapPaneTest");
        primaryStage.show();
    }
	public static void main(String[] args) {
		launch(args);
	}
	   @FXML
	    public TextField text1;
	   @FXML
	    public CheckBox checkbox1,checkbox2;
		@FXML
	    public RadioButton red,green,blue;
		@FXML
	    public RadioButton font_10,font_11,font_12;
		@FXML
	    public TextField text;
		public ToggleGroup g1 = new ToggleGroup();
		public ToggleGroup g2 = new ToggleGroup(); 

	     public void handle(ActionEvent event) {
	    	font_10.setToggleGroup(g2);font_11.setToggleGroup(g2);font_12.setToggleGroup(g2);
	    	red.setToggleGroup(g1);green.setToggleGroup(g1);blue.setToggleGroup(g1);
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
	    @FXML
	    public void handle_1(ActionEvent event) {
	    	if(checkbox1.isSelected() && !checkbox2.isSelected()) text1.setStyle("-fx-font:bold 8pt \"Arial\";");
	    	else if(checkbox2.isSelected() && checkbox1.isSelected())text1.setStyle("-fx-font:bold italic 8pt \"Arial\";");
	    	else if(!checkbox1.isSelected() && checkbox2.isSelected())text1.setStyle("-fx-font: italic 8pt \"Arial\";");
	    	else text1.setStyle("-fx-font: 8pt \"Arial\";");

	    }
	    
}
