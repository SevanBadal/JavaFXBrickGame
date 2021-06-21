/*
 * @author: Sevan Golnazarian
 * The program begins here in LaunchGame.java
 * The root fxml file is passed to the scene, which is then
 * set to the stage.
 * 
 * The program is able to run LaunchGame.java from GameStartController.java if the game ends
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LaunchGame extends Application{
	
public LaunchGame() {
	//this constructor is just to show the order of files loaded in the program
	System.out.println("LaunchGame.java");
}

	/*
	 * the start method from Application is the main entry point into the javafx program
	 * The fxml file is pasted to the scene which is then loaded to the stage (window)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LaunchMenu.fxml"));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Java Final");
		stage.show();
		
		
	}
	public static void main(String[]args) {
		launch(args);
	}

}