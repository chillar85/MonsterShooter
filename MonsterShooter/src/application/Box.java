package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Box extends Pane{
	private Random rnd = new Random();
	private int index;

	public int getIndex() {return index;}

	public void setIndex(int index) {this.index = index;}

	public Box(int index, String imgPath) {
		this.index = index;
		this.imgPath = imgPath;
		createImageView();
	}
	
	private ImageView	imgView;
	private String 		imgPath;

	
	private void createImageView() {
		imgView = new ImageView();
		File file = new File(imgPath);
		Image img = new Image(file.toURI().toString());
		imgView.setImage(img);
		imgView.setX(0);
		imgView.setY(0);
		getChildren().add(imgView);
		setEvent();
	}
	public void setImgSize() {
		imgView.setFitHeight(getPrefHeight());
		imgView.setFitWidth(getPrefWidth());
	}	
	private void setEvent() {
		
		  addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
	  	     @Override
	  	     public void handle(MouseEvent event) {
	  	    	//resetAll();
	  	    	SVars.points += 100;
	  	    	speed = 1;
	  	    	speedRotate = 0;
	  	    	startExplo();
	  	    	System.out.println("Monster killed!");
	  	    	setMouseTransparent(true);
	  	     }
	  	});
		
		}
	
	private void resetAll() {
		setLayoutY(-100);
		setLayoutX(rnd.nextInt(600));
		speed = rnd.nextInt(10)+5;
		if (rnd.nextInt(2) == 1) {
			speedRotate = rnd.nextInt(5)+1;
		}
		else {
			speedRotate = (rnd.nextInt(5)+1) * -1;
		}
		
		changeImg();
		setMouseTransparent(false);
	}
	
	double posY;
	double rotateY;
	int speed = 5;
	int speedRotate = 1;
	void rotate() {
		rotateY = getRotate();
		rotateY += speedRotate;
		setRotate(rotateY);
	}
	
	void moveY() {
		posY = getLayoutY();
		posY += rnd.nextInt(speed);
		setLayoutY(posY);
		if (posY > 600) {
			setLayoutY(-100);
			setLayoutX(rnd.nextInt(600));
			speed = rnd.nextInt(10)+5;
			resetAll();
		}
	}
	
	DataManager dm = new DataManager();
	private void changeImg() {
		  imgPath = System.getProperty("user.dir")+"/img/"+dm.getRandomCharacter();
		  File file  = new File(imgPath);
 		  Image img  = new Image(file.toURI().toString());     
			//4. weise dem imgViewObjekt das Bild zu 
			imgView.setImage(img);
	  }
	
	Timeline exploTime;
	Timeline exploFrameTime;
	int frameCount;
	private void startExplo() {
		frameCount = 0;
		exploFrameTime = new Timeline(new KeyFrame(Duration.seconds(0.11), ev ->{
			setExploImg();
		} ));
		exploFrameTime.setCycleCount(Animation.INDEFINITE);
		exploFrameTime.play();
		
		exploTime = new Timeline(new KeyFrame(Duration.seconds(2), ev ->{
			resetAll();
			exploTime.stop();
		} ));
		exploTime.setCycleCount(Animation.INDEFINITE);
		exploTime.play();
	}
	
	
	
	private void setExploImg() {
		if (frameCount<dm.getAllExploFramesSize()) {
			String str = System.getProperty("user.dir")+"/img/explo/"+dm.getExploFrame(frameCount);
			File file = new File(str);
			Image img = new Image(file.toURI().toString());
			imgView.setImage(img);
		}
		else {
			imgView.setImage(null);
			exploFrameTime.stop();
		}
		frameCount += 1;
	}
}

class DataManager {
	
	ArrayList<String> allCharacters = new ArrayList<String>();
	ArrayList<String> allExploFrames = new ArrayList<String>();
	
	public DataManager() {
		loadData();
	}
	
	void loadData(){
		for (int i = 0; i < 40; i++) {
			allCharacters.add((i+1)+".png");
		}
		for (int i = 0; i < 9; i++) {
			allExploFrames.add("explo_0"+(i+1)+".png");
			
		}
	
	}
	
	  public ArrayList<String> getAllExploFrames(){ return allExploFrames;}
	  public int getAllExploFramesSize(){ return allExploFrames.size();}
	  public String getExploFrame(int index){ return allExploFrames.get(index);}
	
	//========================================================
		public ArrayList<String> getAllCharacters(){ return allCharacters;}
		public int getAllCharactersCount(){ return allCharacters.size();}
		public String getCharacterAt(int index){ return allCharacters.get(index);}
	//=======================================================
		public String getRandomCharacter() {
			double rdouble = Math.random();
			     int index = (int)(rdouble * getAllCharactersCount());
			return allCharacters.get(index);
		}
	
}

