package application;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StartVC {
	
    @FXML	private	TextField	txtUsername;
    @FXML   private	Button		btnStart;
    
    public StartVC() {
		// TODO Auto-generated constructor stub
    	System.out.println("StartVC erzeugt");
	}
    
    public void initialize(){
    	System.out.println("StartVC ready");
    	setAllEvents();
    }
    
    
    EventHandler<MouseEvent> startBtnEvent = new EventHandler<MouseEvent>(){

		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			System.out.println("Start Button Pressed");
			if (txtUsername.getText().isEmpty()) {
				System.out.println("No Username entered");
				txtUsername.requestFocus();
			}
			else {
				System.out.println("Start Game");
				SVars.userName = txtUsername.getText();
				goToNextScene();	
			}
			
		}
    
    };
    
    private void setAllEvents() {
    	btnStart.addEventHandler(MouseEvent.MOUSE_CLICKED, startBtnEvent);
    }
    
    
    
    private void goToNextScene() {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Stage stage = (Stage) btnStart.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }
    
}
