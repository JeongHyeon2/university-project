package application;

import java.util.Random;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ListViewTest extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("ListViewTest.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("숫자 생성기");
			primaryStage.show();
		
		}
		public static void main(String[] args) {
			launch(args);
		}
		   @FXML
		    public Button btn_rotate;
		  	@FXML
		    public Button btn_copy;
		    @FXML
		    public TextArea left;
		    @FXML
		    public TextArea right;
		    String str;
		    @FXML
			public void initialize() {
		    	str="";
		    	for(int i=0;i<45;i++) {
		    		str=str+(i+1)+"\n";
		    		left.setText(str);
		    		
		    	}
			 }
		    @FXML
		    public void rotate(ActionEvent event) {
		    	int count=0,j=0;
		    	Random rnd = new Random();
		    	str="";
		    	int[] arr=new int[20];
		    	for(int i=0;i<20;i++) arr[i]=0;
		    	while(count<20) {
		    		int index=rnd.nextInt(19);//0~44 45개
		    		System.out.println(index);
		    		if(arr[index]==0) {
		    			arr[index]=j;
		    			j++;
		    			count++;
		    			}}
		    
		    	
		    	for(int i=0;i<45;i++) {
		    		str=str+arr[i]+"\n";
		    		left.setText(str);
		    	}
		     }
		    @FXML
		    public void copy(ActionEvent event) {
		    	str=left.getSelectedText();
		    	right.setText(str);
		    }

}
