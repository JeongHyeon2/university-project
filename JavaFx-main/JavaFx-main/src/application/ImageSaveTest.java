package application;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageSaveTest extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("ImageSaveTest.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("ImageSaveTest");
		stage.show();
		System.out.println("20200284 ±èÁ¤Çö");
	}
	public static void main(String[] args) {
		launch(args);
	}
	  @FXML
	    public ImageView imageView;
	  @FXML
	   public Button Save;
	  @FXML
	    public Button Open;
	  Image image;


	    @FXML
	    public void opne_Clicked(ActionEvent event) {
	    	FileChooser fileChooser = new FileChooser();
			setExtFilters(fileChooser);
	        		File file = fileChooser.showOpenDialog(null);
	        		if (file != null) openImage(file);
	    }

	    @FXML
	    public void save_Clicked(ActionEvent event) {
	    	FileChooser fileChooser = new FileChooser();
       		setExtFilters(fileChooser);
       		fileChooser.setTitle("Save Image");
        
       File file = fileChooser.showSaveDialog(null);
       if (file != null) saveImage(file);
	    }
	    private void setExtFilters(FileChooser chooser){
    		chooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPEG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
   		 );
	 }
	    private void openImage(File file){               
	        image = new Image(file.toURI().toString());

	        imageView.setFitHeight(400);
	        imageView.setPreserveRatio(true);
	        imageView.setImage(image);
	        imageView.setSmooth(true);
	        imageView.setCache(true);         
	    }
	    private void saveImage(File file){
	        try {
	            ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), null), "png", file);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    	}


}