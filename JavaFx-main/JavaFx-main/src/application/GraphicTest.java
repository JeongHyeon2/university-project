package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GraphicTest extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("GraphicTest.fxml"))));
		stage.setTitle("GraphicTest");
		stage.show();
	
	}
	public static void main(String[] args) {
		launch(args);
		}
	    @FXML
	    public Canvas canvas;
	    private int x1, y1, x2, y2;
	    private GraphicsContext gc;

	    @FXML
		public void initialize() {
			gc = canvas.getGraphicsContext2D();
			System.out.println("20200284 ±èÁ¤Çö");
		}

	    @FXML
	    public void handle(MouseEvent e) {
	    	if(e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			x1 =(int) e.getX();
			y1 =  (int) e.getY();
		}
		else if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
			x2 = (int) e.getX();
			y2 = (int) e.getY();
			drawShapes(x1,y1,x2,y2);
		}
	}
	
	public void drawShapes(int x1, int y1, int x2, int y2) {
		gc.setStroke(Color.BLACK);
		gc.strokeOval(x1, y1, x2, y2);		
	}
}



