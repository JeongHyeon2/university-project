package application;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

 public class ComboBoxTest extends Application {
			@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("comboBoxTest.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("콤보박스 테스트");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}}
		public static void main(String[] args) {
			launch(args);
		}
		 int num1,num2;
		 double val;
		 String operator;
			@FXML
			public ComboBox x1;
			@FXML
			public ComboBox x2;
			@FXML
			public ComboBox combobox;
			@FXML
			public Label value;
		    @FXML
			 public void initialize(){
		    	 String num[] = {"0","1","2","3","4","5","6","7","8","9"};
		    	 String operator[] = {"+","-","*","/"};
				x1.getItems().addAll(num);	
				x2.getItems().addAll(num);	
				combobox.getItems().addAll(operator);
		    }
		    public void handle_x1() {num1=Integer.parseInt((String)x1.getValue());}
		    
		    public void handle_x2() {num2=Integer.parseInt((String)x2.getValue());}
		    
		    public void handle_operator() {
		    	operator =(String)combobox.getValue();
		    	switch(operator) {
		    	case "+": val=num1+num2; value.setText(val+"");break;
		    	case "-": val=num1-num2; value.setText(val+"");break;
		    	case "*": val=num1*num2; value.setText(val+"");break;
		    	case "/": val=num1/num2; value.setText(val+"");break;
		    	}}
		    
 }
