package application;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class KeyboardTest extends Application implements EventHandler<KeyEvent>{
	private Label blueLabel, redLabel;
	//private Label positionLabel;
	private Point bluePoint, redPoint;
	final int DELTABlue = 100, DELTARed = 10;
	int frameWidth, frameHeight;
	Random rnd = new Random();
	private Pane root;
	private Timer timer = new Timer(); 

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("KeyBoard");
		try {
			root = new Pane();
			root.setPrefSize(500, 500);
			blueLabel = new Label(" ");
			blueLabel.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
			blueLabel.setPrefSize(10, 10);
	
	redLabel = new Label(" ");
	redLabel.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
	redLabel.setPrefSize(10, 10); 			
	
	root.getChildren().addAll(blueLabel, redLabel);
	Scene scene = new Scene(root, 500, 520);
	scene.setOnKeyPressed(this);
	initializeGame();
	primaryStage.setScene(scene);
	primaryStage.show();
} catch (Exception e) {
	e.printStackTrace();
}
}	
	
	public void initializeGame() {
		
		frameWidth = 500;
		frameHeight = 500;
		blueLabel.setLayoutX(rnd.nextInt(frameWidth));
		blueLabel.setLayoutY(rnd.nextInt(frameHeight));
		redLabel.setLayoutX(rnd.nextInt(frameWidth));
		redLabel.setLayoutY(rnd.nextInt(frameHeight));
TimerTask task = new TimerTask() {
        public void run()         {
	runAwayBlueLabel();
        }
};
timer.scheduleAtFixedRate(task,0, 1000); 
	}
	
	public void runAwayBlueLabel() {
		int x = (int)blueLabel.getLayoutX(), y = (int)blueLabel.getLayoutY();
		
		switch (rnd.nextInt(4) + 1) {
		case 1: 	x -= DELTABlue; 
			if(x <= 0) x = 0; break;
		case 2:	x += DELTABlue; 
			int xlimit = frameWidth - (int)blueLabel.getWidth();
			if(x >= xlimit ) x = xlimit; break;
		case 3:	y -= DELTABlue; 
			if(y <= 0) y = 0; break;
		case 4: 	y += DELTABlue; 
			int ylimit = frameHeight - (int)blueLabel.getHeight();
			if(y >= ylimit ) y = ylimit; break;
		}
		blueLabel.setLayoutX(x);
		blueLabel.setLayoutY(y);
	}
	
	@Override
	public void handle(KeyEvent e) {
		int x = (int)redLabel.getLayoutX(), y = (int)redLabel.getLayoutY();
		switch (e.getCode()) {
		case UP:
		y -= DELTARed;
		if (y <= 0) y = 0; break;
		case DOWN:
		y += DELTARed; int ylimit = frameHeight - (int) redLabel.getHeight();
		if (y >= ylimit) y = ylimit; break;
		case LEFT:
		x -= DELTARed; 
		if (x <= 0) x = 0; break;
		case RIGHT:
		x += DELTARed; int xlimit = frameWidth - (int) redLabel.getWidth();
		if (x >= xlimit) x = xlimit; break;
		}	
		
		redLabel.setLayoutX(x);
		redLabel.setLayoutY(y);

		if (isCatched()) {
		timer.cancel();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("알림");
		alert.setHeaderText(null);
		alert.setContentText("잡았습니다!");
		alert.showAndWait();
			}	
		}		
		
		boolean isCatched() {
		Rectangle r1 = new Rectangle();
		r1.setWidth(blueLabel.getWidth());
		r1.setHeight(blueLabel.getHeight());
		r1.setX(blueLabel.getLayoutX());
		r1.setY(blueLabel.getLayoutY());
		return r1.intersects(redLabel.getLayoutX(), redLabel.getLayoutY(), redLabel.getWidth(), redLabel.getHeight());
		}
		
		public static void main(String[] args) {
		launch(args);
		}
}