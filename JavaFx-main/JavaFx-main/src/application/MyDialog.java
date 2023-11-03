package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MyDialog extends Stage{
	@FXML public Button ok;
	@FXML public TextField text;
	private String findString = "";

	public MyDialog() {}	
	public MyDialog(Stage primaryStage) {
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(primaryStage);
		this.setTitle("Ã£±â");
	}
	
	public String showDialog() {
		try {
			Parent scene = FXMLLoader.load(getClass().getResource("MyDialog.fxml"));
			this.setScene(new Scene(scene));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.showAndWait();
		// return findString;
		 findString = ((TextField) this.getScene().lookup("#text")).getText();
		return findString;
	}

	@FXML
	public void handle(ActionEvent e) {
		//findString = text.getText();
		((Stage)((Node)(e.getSource())).getScene().getWindow()).hide();
	}
}



