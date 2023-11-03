package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CheckBoxTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("CheckBoxTest.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("체크박스 테스트");
		stage.show();
	}
	public static void main(String []args) {
		launch(args);
	}

    @FXML
    public CheckBox checkbox1,checkbox2;
    @FXML
    public TextField text;
    @FXML
     public void clicked() {
    	if(checkbox1.isSelected() && !checkbox2.isSelected()) text.setStyle("-fx-font:bold 8pt \"Arial\";");
    	else if(checkbox2.isSelected() && checkbox1.isSelected())text.setStyle("-fx-font:bold italic 8pt \"Arial\";");
    	else if(!checkbox1.isSelected() && checkbox2.isSelected())text.setStyle("-fx-font: italic 8pt \"Arial\";");
    	else text.setStyle("-fx-font: 8pt \"Arial\";");
   }
	    
}
