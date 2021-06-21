/*
 * SCO
 * SFM
 * @author: Sevan Golnazarian
 * GameStartController is loaded by GameStart.fxml
 * the tracker is set to false in the constructor to turn of the computer's 
 * control of the paddle
 * 
 * paddle velocity is controlled by the paddleMovement event handler, which checks for left and right key strokes
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GameStartController extends GameConfig{
	//SCO: *JAVAFX controller classes are not permitted to have
	//parameters in the constructor when fx:controller is declared in the fxml file. 
	public GameStartController() {
		super();
		super.tracker=false;
		
		
	}
	/*
	*SFM: rectKey controls the movement of the paddle
	*rectangleVelocity is added each frame of the Animation, so don't hold it down! 
	*Use quick taps, the friction will slow it down
	 */
	@FXML
    public void paddleMovement(KeyEvent event) {
    	
    	System.out.println(event.getCode());
    	if(event.getCode()==KeyCode.RIGHT) {
    		rectangleVelocity+=5;
    	}
    	if(event.getCode()==KeyCode.LEFT) {
    		rectangleVelocity-=5;
    	}
    	 
    }
	@FXML
	private Circle ball;
	
    @FXML
    private Pane pane;
    
    @FXML
    private TextField lives;
    
    @FXML
    private TextField score;
    
    //SFM: unique field
    //the visibility of menuButton is set to false until all lives are lost or all bricks are gone
    @FXML
	private Button menuButton;
    
    //SFM
    private Parent menuRoot;
    private Scene menu;
    private Stage stage;
    
    /*
    *SFM:unique method
    *when the menuButton is clicked, the menu scene is loaded from LaunchMenu.fxml
     */
    @FXML
    void menuReset(ActionEvent event) throws Exception {
    	//
    	menuRoot = FXMLLoader.load(getClass().getClassLoader().getResource("LaunchMenu.fxml"));
    	menu = new Scene(menuRoot);
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(menu);
    	stage.show();

    }
   
	
    	
    

}
