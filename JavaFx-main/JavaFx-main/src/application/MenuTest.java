package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MenuTest extends Application {
	 
		@FXML public MenuItem about, exit;
		@FXML public RadioMenuItem red, green, blue, black, arial, cambria, corbel;
		@FXML public CheckMenuItem bold, italic;
		@FXML public Label testMsg;
		@FXML public ToggleGroup color;
		@FXML public ToggleGroup font;

	   
		@Override
		public void start(Stage primaryStage) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("MenuTest.fxml"));
			Scene scene = new Scene(root);		

			primaryStage.setTitle("FXML Welcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		public static void main(String[] args) {
			launch(args);
		}
		public void initialize() {
			System.out.println("20200284 김정현");
		}
		@FXML
		public void handle(ActionEvent e) {
			if (e.getSource() == about) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("About"); 	
				alert.setHeaderText(null);
				alert.setContentText("메뉴 예제 프로그램입니다.");
				alert.showAndWait(); }
			else if (e.getSource() == exit) System.exit(0);
			else if (e.getSource() == red) testMsg.setTextFill(Color.RED);
			else if (e.getSource() == green) testMsg.setTextFill(Color.GREEN);
			else if (e.getSource() == blue) testMsg.setTextFill(Color.BLUE);
			else if (e.getSource() == black) testMsg.setTextFill(Color.BLACK);
			else if (e.getSource() == arial) testMsg.setFont(Font.font("Arial", 64));
			else if (e.getSource() == cambria) testMsg.setFont(Font.font("Cambria", 64));
			else if (e.getSource() == corbel) testMsg.setFont(Font.font("Corbel", 64));
			else if (e.getSource() == bold) setFont();
			else if (e.getSource() == italic) setFont();
		}
		public void setFont() {
			testMsg.setFont(Font.font(testMsg.getFont().getFamily()
				,bold.isSelected() ? FontWeight.BOLD : FontWeight.NORMAL,
				italic.isSelected() ?  FontPosture.ITALIC : FontPosture.REGULAR, 64));
		}
		
		
}


