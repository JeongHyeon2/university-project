package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PixelTest extends Application{
	@FXML public Button load;
	@FXML public ImageView imageView, imageViewDest;
	@FXML public Label label;
	
	@Override
	public void start(Stage stage) throws Exception {
	    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("PixelTest.fxml"))));
	    stage.setTitle("PixelTest");
	    stage.show();
	    System.out.println("20200284 ±èÁ¤Çö");
	}
	public static void main(String[] args) {
		launch(args);
	}

	@FXML
	public void load_Clicked(ActionEvent e) {
	    Image image = new Image("file:C:\\Windows\\Web\\Screen\\img100.jpg");
               imageView.setImage(image);
    //copy image
    PixelReader pixelReader = image.getPixelReader();
    WritableImage writableImage = new 	WritableImage((int)image.getWidth(),(int)image.getHeight());
    PixelWriter pixelWriter = writableImage.getPixelWriter();
    for(int y=0; y<image.getHeight(); y++){
        for(int x=0; x<image.getWidth(); x++){
            Color color = pixelReader.getColor(x, y);
            color = color.brighter();
            pixelWriter.setColor(x, y, color);
        }
    }
    imageViewDest.setImage(writableImage);

    label.setText(image.getWidth() + " x " + image.getHeight());}

	@FXML
	public void imageView_Clicked(MouseEvent e) {
	imageView.setImage(null);
    		imageViewDest.setImage(null);
    		label.setText("");
	}

}
