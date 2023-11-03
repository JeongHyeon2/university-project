package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;


public class TextareaTest extends Application {
	@Override
		public void start(Stage primaryStage) throws Exception {
		
			Parent root = FXMLLoader.load(getClass().getResource("TextareaTest.fxml"));
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("텍스트 테스트");
			primaryStage.setScene(scene);
			primaryStage.show();	
		}
	public static void main(String[] args) {
		launch(args);
	}
		@FXML
	      public Button copy1,copy2;
		@FXML
	      public TextArea text1,text2;
	  
		 public void handle(MouseEvent e) {
 	      String str;
			if(e.getSource() == copy1) {
				str = text2.getText();
				text2.setText( str + text1.getSelectedText() );
			}
			else if (e.getSource() == copy2) {
				str = text1.getText();
				text1.setText( str + text2.getSelectedText() );	
			}		
	}
}




