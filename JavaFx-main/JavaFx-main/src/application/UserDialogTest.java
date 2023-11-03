package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserDialogTest extends Application{
	public Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("UserDiapog.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	
		@FXML
	    public Button bt;
		@FXML
		public void initialize() {
			System.out.println("20200284 ±èÁ¤Çö");
		}

	    @FXML
	    public void handle(ActionEvent event) {
	    	MyDialog dlg = new MyDialog(primaryStage);
			String res = dlg.showDialog();
			if (res  != "") bt.setText(res);

	    }
	

}
