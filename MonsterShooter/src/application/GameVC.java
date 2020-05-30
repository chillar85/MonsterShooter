package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameVC {

    @FXML	private 	TextField	txtTimer;
    @FXML	private 	TextField	txtScore;
    @FXML	private		Pane		MainBox;
    @FXML	private		Pane 		GameBox;
    @FXML	private		ImageView	layer1;
    @FXML	private		ImageView	layer2;
    @FXML	private		ImageView	layer3;
    @FXML	private		ImageView	layer4;
    @FXML	private		ImageView	layer5;
    @FXML	private		ImageView	layer6;
    @FXML	private		ImageView	layer7;
  
    
    private Timeline timer = new Timeline();
    private int loops = 0;
    private int time = 99;

    ArrayList<Box> monsterList = new ArrayList<Box>();

    
    public GameVC() {
  		// TODO Auto-generated constructor stub
      	System.out.println("GameVC erzeugt");
  	}
    Box box;
    DataManager dm = new DataManager();
    public void initialize(){
      	System.out.println("GameVC ready");
      	setAllGameParams();
      	createMonster();

    }
    
    private void createMonster() {
      	box = new Box(0, System.getProperty("user.dir")+"/img/"+dm.getRandomCharacter());
      	box.setLayoutX(100);
      	box.setLayoutY(100);
      	box.setPrefHeight(100);
      	box.setPrefWidth(100);
      	box.setImgSize();
      	GameBox.getChildren().add(box);
      	monsterList.add(box);      	
      	
    }
    
    private void setAllGameParams() {
    	
    	time = 99;
    	SVars.points =  0;
    	txtTimer.setText(Integer.toString(time));
    	txtScore.setText(Integer.toString(SVars.points));
    	
    	timer = new Timeline(new KeyFrame(Duration.seconds(0.02), ev -> {
	        
	        loops += 1;
	        if (loops%50 == 0) {
	        	countTime();
			}
	        if (loops%250 == 0) {
	        	createMonster();
	        }
	        for (Box box : monsterList) {
				box.rotate();
				box.moveY();
			}	             
	        txtScore.setText(Integer.toString(SVars.points));
    	}));
    	timer.setCycleCount(Animation.INDEFINITE);
	    timer.play();
    }
    
    private void countTime() {
    	time -= 1;
    	txtTimer.setText(Integer.toString(time));
    	if (time <= 0) {
			timer.stop();
			goToNextScene();
		}
    	
    }
    
    private void goToNextScene() {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("End.fxml"));
            Stage stage = (Stage) MainBox.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }
}
