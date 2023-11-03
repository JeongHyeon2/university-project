package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Test extends Application  {
		@FXML public TableView tableView;
		@FXML public TableColumn column1, column2;
		
		@Override
		public void start(Stage primaryStage) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
			Scene scene = new Scene(root);		
			primaryStage.setTitle("FXML Welcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		}

	public static void main(String [] args) {
		launch(args);
	}
	@FXML
	public void initialize() {
		column1.setCellValueFactory(new PropertyValueFactory<>("name"));
		column2.setCellValueFactory(new PropertyValueFactory<>("address"));

		ObservableList<Person> data = FXCollections.observableArrayList(new Person("±èº´¸¸", "±¸¹Ì½Ã"),
				new Person("±èÈ«¸®", "±èÃµ½Ã"));
		Person p = new Person();
		p.setAddress("¿ï»ê");
		p.setName("±èÁ¤Çö");
		ObservableList<Person> data1 = FXCollections.observableArrayList(p);
		tableView.setItems(data);
		tableView.setItems(data1);
	}
	
}



