package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class 과제2 extends Application {
	@Override
		public void start(Stage primaryStage) throws Exception {
		
			Parent root = FXMLLoader.load(getClass().getResource("과제2.fxml"));
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("버튼 이벤트 테스트");
			primaryStage.setScene(scene);
			primaryStage.show();	
		}
	public static void main(String[] args) {
		launch(args);
	}
	 private String str;
		private int n=0;
		    @FXML
		    public TextField text;
		    
		    @FXML
		    public Label label;
		    @FXML
		    public Button btn;
		   

		    @FXML
		    public void TextChange(KeyEvent event) {
		    	str=text.getText();
		    }
		    @FXML
		    public  void print(MouseEvent event) {
		    if(n==0) { label.setText(str.toLowerCase());n=1; }
		    else if(n==1) { label.setText(str.toUpperCase());n=0;}
		   }

	
}

