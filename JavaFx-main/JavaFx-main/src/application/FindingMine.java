package application;

import java.util.Random;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FindingMine extends Application {
	int x=10,y=10; // ���� ����
	int[][] arr = new int[x][y]; // �迭
	int mine=10;//���ڰ���
	int mouseX,mouseY;//���콺 x,y ��ġ
	int posX,posY; // ���콺  ��� ��ġ
	Random rnd = new Random();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FindingMine.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("����ã��");
		primaryStage.show();
	}
	@FXML public Canvas canvas;
	@FXML
	public void initialize() {
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				arr[i][j]=0;
			}
		}
		//���� ���� ����=1 �ƴϸ� 0
		for(int i=0;i<mine;i++) {
		arr[rnd.nextInt(x-1)][rnd.nextInt(y-1)]=1;
		}
	}
	@FXML
    public void Clicked(MouseEvent event) {
		GraphicsContext gc1 = canvas.getGraphicsContext2D();
		GraphicsContext gc2 = canvas.getGraphicsContext2D();
		mouseX=(int) event.getX();
		mouseY=(int) event.getY();
		posX=mouseX/80;
		posY=mouseY/80;
		gc1.setFont(new Font("Arial", 20));
		if(event.isPrimaryButtonDown()) {
			for(int i=0;i<x*y;i++) {
			if(arr[posX][posY]==0) {
				String n=Integer.toString(findingmine(posX,posY));
				gc1.strokeText(n, posX*80+35, posY*80+45);
			}
			else {
				gc2.setFill(Color.RED);
				gc2.fillRect(posX*80+10,posY*80+10, 60, 60);
				gc2.strokeText("����\n����", posX*80+20, posY*80+35);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����!");
				alert.setHeaderText(null);
				alert.setContentText("���ڸ� ã�ҽ��ϴ�!");
//				alert.showAndWait();
//				System.exit(0);
			}}
			}
		else {gc1.strokeText("����\n����", posX*80+20, posY*80+35);	
	 }}
	//���� ���� ã��
	public int findingmine(int x,int y) {
		int minenum=0;//���� ��
		if(x==0&&y!=0) {
			if(x==0&&y==9) {
				if(arr[x][y]==1) minenum++;
				if(arr[x][y-1]==1)minenum++;
				if(arr[x+1][y]==1)minenum++;
			}
			else{
			if(arr[x][y]==1) minenum++;
			if(arr[x][y-1]==1)minenum++;
			if(arr[x+1][y+1]==1)minenum++;
			if(arr[x+1][y]==1)minenum++;
			if(arr[x][y+1]==1)minenum++;
			}
		}
		else if(x==9&&y!=9) {
			if(x==9&&y==0) {
				if(arr[x][y]==1) minenum++;
				if(arr[x-1][y]==1)minenum++;
				if(arr[x][y+1]==1)minenum++;
			}
			else{
			if(arr[x][y]==1) minenum++;
			if(arr[x-1][y]==1)minenum++;
			if(arr[x][y-1]==1)minenum++;
			if(arr[x-1][y-1]==1)minenum++;
			if(arr[x][y+1]==1)minenum++;}
		}
		else if(x!=0&&y==0) {
			if(arr[x][y]==1) minenum++;
			if(arr[x-1][y]==1)minenum++;
			if(arr[x+1][y+1]==1)minenum++;
			if(arr[x+1][y]==1)minenum++;
			if(arr[x][y+1]==1)minenum++;
		}
		else if(x==0&&y==0) {
			if(arr[x][y]==1) minenum++;
			if(arr[x+1][y+1]==1)minenum++;
			if(arr[x+1][y]==1)minenum++;
			if(arr[x][y+1]==1)minenum++;
		}
		else if(x!=9&&y==9) {
			if(arr[x][y]==1) minenum++;
			if(arr[x-1][y]==1)minenum++;
			if(arr[x][y-1]==1)minenum++;
			if(arr[x-1][y-1]==1)minenum++;
			if(arr[x+1][y]==1)minenum++;
		}
		else if(x==9&&y==9) {
			if(arr[x][y]==1) minenum++;
			if(arr[x-1][y]==1)minenum++;
			if(arr[x][y-1]==1)minenum++;
			if(arr[x-1][y-1]==1)minenum++;
		}
		else 
		{
			if(arr[x][y]==1) minenum++;
			if(arr[x-1][y]==1)minenum++;
			if(arr[x][y-1]==1)minenum++;
			if(arr[x-1][y-1]==1)minenum++;
			if(arr[x+1][y+1]==1)minenum++;
			if(arr[x+1][y]==1)minenum++;
			if(arr[x][y+1]==1)minenum++;
		}
		return minenum;
	}
}
