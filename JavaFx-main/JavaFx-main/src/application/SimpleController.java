package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class SimpleController {
	private Button button1;

    @FXML
    void clicked(MouseEvent event) {
    	button1.setText("¿À");

    }

}
