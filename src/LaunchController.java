/*
 * SFM unique fields and methods
 * SCO
 * @author: Sevan Golnazarian
 * LaunchController.java is launched from LaunchMenu.fxml
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LaunchController extends GameConfig{
	//SCO: *JAVAFX controller classes are not permitted to have
	//parameters in the constructor when fx:controller is loaded from the fxml file. 
	public LaunchController() {
		System.out.println("LaunchController.java");
		this.tracker=true;
		this.rowsInBrickField=5;
	
	}
	
	//SFM unique fields
	private Parent scene2Root;
	private Scene scene2;
	private Stage stage;
	
	
	/*
    *SFM: unique method in the subclass
	*buttonPressed is called when the user clicks on the on screen button
	*LaunchController.java then loads a new scene with GameStart.fxml
	*GameStart.fxml then loads a new controller class
	*/
	  @FXML
	    void buttonPressed(ActionEvent event) throws Exception {
	    	scene2Root = FXMLLoader.load(getClass().getResource("GameStart.fxml"));
	    	scene2 = new Scene(scene2Root);
	    	
	    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	//getSouce from event doesn't know what the return type is so it must be cast to node
	    	
	    	stage.setScene(scene2);
	    	
	    	stage.show();
	    	
	  
	    }
    
}
