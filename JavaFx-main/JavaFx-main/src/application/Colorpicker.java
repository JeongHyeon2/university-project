package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Colorpicker extends Application{	

	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("ColorPicker.fxml"))));
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	@FXML public ColorPicker chooser;
	@FXML public Pane pane;
	@FXML
	public void initialize() {
		System.out.println("20200284 ±èÁ¤Çö");
	}
	@FXML 
	public void handle(ActionEvent e){
		BackgroundFill backgroundFill = new BackgroundFill(chooser.getValue(), CornerRadii.EMPTY, Insets.EMPTY);

		pane.setBackground(new Background(backgroundFill));
	}
}



