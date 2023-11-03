package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ImageTest extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("ImageTest.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("ImageTest");
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	@FXML public Canvas canvas;	
	@FXML public Button button;
	private Image image;
	private GraphicsContext gc;
	@FXML
	public void initialize() {
		System.out.println("20200284 ±èÁ¤Çö");
		gc = canvas.getGraphicsContext2D();
		String imagePath = "file:C:\\Windows\\Web\\Screen\\img100.jpg";
		image = new Image(imagePath);
	}
	@FXML
	public void handle(ActionEvent e) {
		gc.drawImage(image, 10, 10, canvas.getWidth()-10,canvas.getHeight());
	}
}
