package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ImageViewTest extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("ImageViewTest.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("ImageViewTest");
		stage.show();
		System.out.println("20200284 ±èÁ¤Çö");
	}
	public static void main(String[] args) {
		launch(args);
	}
	@FXML ImageView iv2, iv3;
	@FXML
	public void initialize() {
		
	}

}
