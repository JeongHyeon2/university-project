package application;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class KeyboardGame extends Application{
	int framewidth = 1230, frameheight = 660;
	int redX; int redY;
	public Timer timer = new Timer(); 
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("KeboardGame.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("키보드 게임");
		stage.show();
		}
	public static void main(String[] args) {
		launch(args);
	}

    @FXML
    public Button red;
    @FXML
    public Label blue;
    @FXML
    public AnchorPane pane;
   
    Random rnd = new Random();
    public void blueRun() {
    	 int blueX = (int)blue.getLayoutX(); int blueY = (int)blue.getLayoutY();
    	 
    	switch(rnd.nextInt(4)) {
    	case 0: if(blueY>50) blueY-=100; else blueY=0;
    		break;
    	case 1:if(blueY<frameheight)blueY+=100; else blueY= frameheight;
    		break;
    	case 2:if(blueX<framewidth) blueX+=100; else  blueX=framewidth;
    		break;
    	case 3:if(blueX>50)blueX-=100; else blueX=0;
    		break;
    	}
    	blue.setLayoutX(blueX);
    	blue.setLayoutY(blueY);
    	
    }
	@FXML
	public void initialize() {
		redX=(int)red.getLayoutX();  redY=(int)red.getLayoutY();
		TimerTask task = new TimerTask() {
				public void run() {
					blueRun();
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000);	//task, delay, period
		
	}
	@FXML
	public void redRun(KeyEvent e) {
		int x=(int)red.getLayoutX();  int y=(int)red.getLayoutY();
		switch(e.getCode()) {
		case UP: if(y>50) y-=100; else y=0; 
			break;
		case DOWN: if(y<frameheight) y+=100; else y=frameheight;
			break;
		case RIGHT: if(x<framewidth) x+=100; else x=framewidth;
			break;
		case LEFT: if(x>50) x-=100; else x=50;
			break;
			}
		red.setLayoutX(x);
		red.setLayoutY(y);
		if(isCatched()) {Stop();}
	}

	boolean isCatched() {
		double distanceX = red.getLayoutX()-blue.getLayoutX();
		double distanceY = red.getLayoutY()-blue.getLayoutY();
		if(distanceX<0) distanceX = (-1)*distanceX;
		if(distanceY<0) distanceY = (-1)*distanceY;
		if(distanceX<=50 && distanceY<=50) { return true; }
		else return false;
		}
	void Stop() {
		System.out.println("잡았음");
		timer.cancel();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("종료");
		alert.setHeaderText(null);
		alert.setContentText("잡았습니다!");
		alert.showAndWait();
		}
}



