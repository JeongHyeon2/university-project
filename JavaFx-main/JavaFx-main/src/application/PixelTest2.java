package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PixelTest2 extends Application{
	@FXML public Button button;
	@FXML public ImageView imageView, destImageView;
	@FXML public Image src;
	
	@Override
	public void start(Stage stage) throws Exception {
	    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("PixelTest2.fxml"))));
	    stage.show();
	    System.out.println("20200284 ±èÁ¤Çö");
	}
	public static void main(String [] args) {
		launch(args);
	}
	
	@FXML
	public void handle(ActionEvent e) {
		MyImage image = new MyImage(src);
    	WritableImage img = image.convertToGray();
    	destImageView.setImage(img);
    }
}

class MyImage{
	private PixelReader reader;
	private PixelWriter writer;
	int width, height;
	WritableImage dest;
	public MyImage(Image src) {
		reader = src.getPixelReader();
		width = (int)src.getWidth();
		height = (int)src.getHeight();
		dest = new WritableImage(width, height);
		writer = dest.getPixelWriter();
		
		Color color;
		for (int x = 0; x < width; x++) {
	        		for (int y = 0; y < height; y++) {
	        	color = reader.getColor(x, y);
	        	writer.setColor(x, y, color);
	        		}
	    	}
	}
	public WritableImage convertToGray() {
		   Color color, gray;
		    for (int x = 0; x < width; x++) {
		        for (int y = 0; y < height; y++) {
		        color = reader.getColor(x, y); 
		        	
		        int red = (int) (color.getRed()*255);
		        int green = (int) (color.getGreen()*255);
		        int blue = (int) (color.getBlue()*255);

		         int grayLevel = (int) Math.sqrt((red*red + green*green + blue*blue)/3.0);	         gray = Color.rgb(grayLevel, grayLevel, grayLevel);
		         writer.setColor(x, y, gray);
		        }
		    }
		    return dest;
		}
		
		public WritableImage converToBinary() {
			return null;
		}
}




