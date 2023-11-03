package application;

import java.awt.Point;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MouseTest extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MouseEvent.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("MouseTest");
		stage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}

    @FXML
    public Button button;

    @FXML
    public TextField textfield1;

    @FXML
    public TextField Pannel;

    @FXML
    public TextField label;
    @FXML
    public AnchorPane pane;
    @FXML
    public void Clicked1(MouseEvent event) {
    	label.setText( "Clicked " + event.getX() + " , " +  event.getY()+" in TextField");
    }@FXML
    public void Clicked2(MouseEvent event) {
    	label.setText( "Clicked " + event.getX() + " , " +  event.getY()+" in Pannel");
    }@FXML
    public void Clicked3(MouseEvent event) {
    	label.setText( "Clicked " + event.getX() + " , " +  event.getY()+" in Button");
    }@FXML
	public void Clicked4(MouseEvent event) {
		label.setText( "Clicked " + event.getX() + " , " +  event.getY()+" in AnchorPane");
	}
    @FXML
    public void Dragged1(MouseEvent event) {
    	Point bx = new Point((int)textfield1.getLayoutX() , (int)textfield1.getLayoutY());
    	textfield1.setLayoutX(bx.x+ event.getX());
    	textfield1.setLayoutY(bx.y+ event.getY());
		label.setText("textfield1 Dragged : " + event.getX() + ", " + event.getY() );
    }    
    @FXML
    public void Dragged2(MouseEvent event) {
	Point bx = new Point((int)Pannel.getLayoutX() , (int)Pannel.getLayoutY());
	Pannel.setLayoutX(bx.x+ event.getX());
	Pannel.setLayoutY(bx.y+ event.getY());
	label.setText("Pannel Dragged : " + event.getX() + ", " + event.getY() );
	}  
    @FXML
    public void Dragged3(MouseEvent event) {
	Point bx = new Point((int)button.getLayoutX() , (int)button.getLayoutY());
	button.setLayoutX(bx.x+ event.getX());
	button.setLayoutY(bx.y+ event.getY());
	label.setText("Button Dragged : " + event.getX() + ", " + event.getY() );
	}
    @FXML
    public void Dragged4(MouseEvent event) {
    	Point bx = new Point((int)button.getLayoutX() , (int)button.getLayoutY());
    	button.setLayoutX(bx.x+ event.getX());
    	button.setLayoutY(bx.y+ event.getY());
    	label.setText("AnchorPane Dragged : " + event.getX() + ", " + event.getY() );
 }
    @FXML
    public void Moved1(MouseEvent event) {
    	label.setText( "Moved " + event.getX() + " , " +  event.getY()+" in TextField");
    }  @FXML
    public void Moved2(MouseEvent event) {
	label.setText( "Moved " + event.getX() + " , " +  event.getY()+" in Pane");
	}  @FXML
    public void Moved3(MouseEvent event) {
	label.setText( "Moved " + event.getX() + " , " +  event.getY()+" in Button");
	}@FXML
	public void Moved4(MouseEvent event) {
		label.setText( "Moved " + event.getX() + " , " +  event.getY()+" in AnchorPane");
	}
	


}
