package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Filechooser extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FileChooser.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("FileChooser");
		stage.show();
		}
	public static void main(String[] args) {
		launch(args);
	}
    @FXML
   public ImageView image;

    @FXML
    public MenuItem open;
    @FXML
    public void initialize() {
    	System.out.println("20200284 김정현");
    }

    @FXML
    public void handle(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();// 지원할 확장자 추가
    	fileChooser.getExtensionFilters().addAll(
    			new FileChooser.ExtensionFilter("BMP File (*.bmp)", "*.bmp"),new FileChooser.ExtensionFilter("JPG File (*.jpg)", "*.jpg"),new FileChooser.ExtensionFilter("GIF File (*.gif)", "*.gif"));

   
	File file = fileChooser.showOpenDialog(null);
	if (file != null) {			
		try {				
			image.setImage(new Image(new FileInputStream(file)));	
		} 
		catch (FileNotFoundException e1) {
			e1.printStackTrace();			
		}		
	}	
    }

}


