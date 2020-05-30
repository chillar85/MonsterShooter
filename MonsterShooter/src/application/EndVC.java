package application;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EndVC {

    @FXML	private 	AnchorPane	MainBox;
    @FXML   private 	Button 		btnTryAgain;
    @FXML   private 	Pane 		scoreBox;
    @FXML	private 	TextField	txtTimer;
    @FXML	private 	TextField	txtScore;
    @FXML	private		Pane 		GameBox;
    @FXML	private		ImageView	layer1;
    @FXML	private		ImageView	layer2;
    @FXML	private		ImageView	layer3;
    @FXML	private		ImageView	layer4;
    @FXML	private		ImageView	layer5;
    @FXML	private		ImageView	layer6;
    @FXML	private		ImageView	layer7;
    @FXML	private		Label		lblGameOver;
    
    public EndVC() {
		// TODO Auto-generated constructor stub
    	System.out.println("EndVC erzeugt");
	}
    
    public void initialize(){
    	System.out.println("EndVC ready");
    	setAllEvents();
    	lblGameOver.setText(SVars.userName+"  got "+SVars.points+" Points!");
    	
    }
    
    
    EventHandler<MouseEvent> startBtnEvent = new EventHandler<MouseEvent>(){

		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			System.out.println("Try Again Button Pressed");
			goToNextScene();
		}
    	
    };
    
    private void setAllEvents() {
    	btnTryAgain.addEventHandler(MouseEvent.MOUSE_CLICKED, startBtnEvent);
    }
    
    
    
    private void goToNextScene() {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Stage stage = (Stage) btnTryAgain.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }
}
