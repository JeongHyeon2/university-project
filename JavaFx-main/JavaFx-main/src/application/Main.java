package application;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	int turn=1; // 1-��  0-��
	int MousePointX,MousePointY; //���콺 ����Ʈ ��ġ
	double x,y; // ���� ��ġ
	final int w=19; // ĭ�� ����
	double [] point = new double[w+1];// �� ��ġ �迭
	@Override
	public void start(Stage primaryStage) throws Exception{
	
			Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			}
	public static void main(String[] args) {
		launch(args);
	}
	@FXML public Canvas canvas;
	@FXML
	public void initialize() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		 x=canvas.getWidth()/w;
		 y=canvas.getHeight()/w;
			for(int i=0;i<=w;i++) {
			point[i]=i*x;
			gc.strokeLine(point[i], 0, point[i], canvas.getHeight());
			gc.strokeLine(0, point[i], canvas.getWidth(), point[i]);
			}
	}
	@FXML
	public void handle(MouseEvent e) {
		x=(int)e.getX();  y = (int)e.getY();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		if(e.isPrimaryButtonDown()) {
			 if(turn==1) drawWhite(MousePointX,MousePointY);
			 else drawBlack(MousePointX,MousePointY);
		} else System.out.println("����"); // ���� �ڵ�
		
	}
	
	// ��� �� �׸���
	public void drawWhite(int x,int y) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.BLACK);
		gc.fillOval(this.x-15, this.y-15, 30, 30);
		gc.strokeOval(this.x-15, this.y-15, 30, 30);
	    turn=0;
	}
	//������ �� �׸���
	public void drawBlack(int x,int y) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillOval(this.x-15, this.y-15, 30, 30);
		turn=1;
		}
	
}




    
