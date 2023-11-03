package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PopUpMenu extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("PopUpMenu.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("PopUpMenuTest");
		stage.show();
	}
	public static void main(String [] args) {
		launch(args);
	}
	 	@FXML
	    public RadioMenuItem red,green,blue,black;
	 	@FXML
	    public ToggleGroup color;
	 	@FXML
	    public Label textMsg;
	 	@FXML
	 	public void initialize() {
	 		System.out.println("20200284 ±èÁ¤Çö");
	 	}

	    @FXML
	    public void handle(ActionEvent e) {
	    	if (e.getSource() == red) textMsg.setTextFill(Color.RED);
			else if (e.getSource() == green) textMsg.setTextFill(Color.GREEN);
			else if (e.getSource() == blue) textMsg.setTextFill(Color.BLUE);
			else if (e.getSource() == black) textMsg.setTextFill(Color.BLACK);
	    	}

}
