/*
 *ARR: use of an array to print bricks to the screen
 *LPS: multiple nested for loops that check collisions
 *IFE: nested if else and if statements that check collisions and boundaries
 *RCL: GameConfig is considered the driver class of Bricks and CollisionTool
 *STM: String methods used to parse data sent to textFields 
 * @author: Sevan Golnazarian
 * GameConfig is the runner class for Bricks
 * 
 */
import java.security.SecureRandom;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameConfig {
	public GameConfig() {
		System.out.println("GameConfig.java");
		this.tracker=true;
	}
	
	//these fields are accessible from subclasses but not from within Bricks
	
	protected double rectangleVelocity=1;
	protected final int minX = 5;
	//used to check the left bounds on the screen
	//bounds function for the pane yields inaccurate results
	protected final int maxX = 595;
	//used to check the right bounds on the screen
	//bounds function for the pane yields inaccurate results
	protected boolean tracker;
	//the tracker is true when the bricks are all gone or there are no more lives left
	protected boolean reset = false;
	//reset is true when there are no more bricks on the field and only when the tracker is false 
	//(to prevent the reset button from appearing when the paddle is controlled by the computer
	
	protected int livesKeeper = 5;
	protected int scoreKeeper=0;
	
	//rowsInBrickField is passed to the parameter constructor for the Bricks class
	protected int rowsInBrickField=4;
	
	
		@FXML
		private Rectangle hitBox;
	
	 	@FXML
	    private Pane pane;

	    @FXML
	    private Rectangle paddle;

	    @FXML
	    private Circle ball;
	    
	    @FXML
	    private Button button;
	    
	    @FXML
	    private TextField score;
	    
	    @FXML
	    private TextField lives;
	    
	    @FXML
	    private Button menuButton;
	    
	    
	 

    //initialize() is called after the constructor and fxml annotated fields are loaded
    public void initialize() {
    	
    	//paddle.setStroke and ball.setStroke allow the outline of the ball and paddle to be consistent with their fill color
    	paddle.setStroke(paddle.getFill());
    	ball.setStroke(ball.getFill());
    	
    	//center the ball on screen based on the 600x600 layout
    	ball.setLayoutX(300);
    	ball.setLayoutY(300);
    	
    	//the second argument of Bricks is the number of rows in the brick field
    	Bricks bricks = new Bricks(pane,rowsInBrickField);
    	//brickField(pane) sets up the field of bricks according to the pane dimensions
    	bricks.brickField(pane);
    	//bricks.setBrickRowCount(4);
    	SecureRandom random = new SecureRandom();
    	
    	//collisions object is created to check boundaries and collisions 
    	CollisionTool collisions = new CollisionTool(minX,maxX,ball, paddle);
    	
    	//randomSpeedMultiplier saves a random number between 0 and 10 before the first loop 
    	//when the ball hits a brick in the brick field, another random number is generated
    	//if these two numbers are equal to each other, the brick gains a speed boost
    	int randomSpeedMultiplier = random.nextInt(10);
    	System.out.println("Speed Boost Value Key: "+randomSpeedMultiplier);
    
    	//bricks from the brick field are added to the pane/scene
    	//ARR
    	//LPS
    	for(int i = 0; i<bricks.brickFieldArray.length;i++) {
    	pane.getChildren().add(bricks.brickFieldArray[i]);
    	}
    	
    	AnimationTimer timer = new AnimationTimer() {
    		//these variables are local to the animation timer and used in the overridden handle method
    		//a random number between 0 and 2 is added to the dx and dy to create speed variance between game sessions
    		double dx = 2+random.nextInt(2);
    		double dy= 2+random.nextInt(2);
    		int velocity = 60;
    		long previousTime = System.nanoTime();
    		
    		
    		//Everything in inside the body of handle will be called during each frame of the animation timer
			@Override
			public void handle(long now) {
				double elapsedTime = (now-previousTime)/1000000000.0;
				previousTime = now;
				double scale = elapsedTime*velocity;
				Bounds bounds = pane.getBoundsInLocal();
				
				//starts moving the ball according to delta x and y with an applied scale
				ball.setLayoutX(ball.getLayoutX()+dx*scale);
				ball.setLayoutY(ball.getLayoutY()+dy*scale);
				
				//the paddle is incremented based on the amount of velocity added per key stroke to create a gliding effect
				paddle.setLayoutX((paddle.getLayoutX()+rectangleVelocity));
				//the velocity is decreased by 5% to mimic friction and slow the rectangle down
				//(because it is on a loop, without friction, the paddle will keep moving unless counter acted by an opposite keystroke)
				rectangleVelocity*=0.95;
				
				//tracker is initiated via the constructor 
				//disables arrow key controls
				//allows the computer to take of the paddle movement
				if(tracker) {
					//if the x value of the center of the ball is to the right of the center of the paddle
					//then move the paddle to the right at the same speed as the ball is moving
				if(((paddle.getLayoutX()+paddle.getWidth()/2))<ball.getLayoutX()+ball.getRadius()) {
				
					paddle.setLayoutX(paddle.getLayoutX()+Math.abs(dx)*scale);
					
		    		}
				//if the x value of the center of the ball is to the left of the center of the paddle
				//then move the paddle to the left at the same speed as the ball is moving
		    		if(((paddle.getLayoutX()+paddle.getWidth()/2))>ball.getLayoutX()+ball.getRadius()) {
		    			
		    			paddle.setLayoutX(paddle.getLayoutX()-Math.abs(dx)*scale);
		    			
		   		}
		    		
				}//end of tracker
				
				//if the tracker is not on, then score and lives are set to their corresponding text fields
				if(!tracker) {
					score.setText(Integer.toString(scoreKeeper));
					lives.setText(Integer.toString(livesKeeper));
					}
				
				//checks if all bricks have been removed
				//if true, the tracker turns on and the reset button is made visible
				if(bricks.brickFieldArray.length==scoreKeeper&&!tracker) {
					menuButton.setVisible(true);
					tracker=true;
					reset = true;
					
				}
				//if bricks are still on the screen but there are no lives left
				//then the tracker is turned on and the reset button is made visible
				else if(livesKeeper<=0) {
					tracker=true;
					menuButton.setVisible(true);
					reset = true;
				}
				
				//******Start of collision checking******
				
				//ball and side wall collisions
				if(collisions.ballLeftRightWall()) {
					dx*=-1;
					if(ball.getLayoutX()-ball.getRadius()<=0) {
						ball.setTranslateX(ball.getLayoutX()+ball.getRadius()+2);
					}
				}
				//ball and bottom wall collision
				if(collisions.ballBottomWall(bounds)) {
					ball.setLayoutX(300);
					ball.setLayoutY(300);
					if(dx>3.5||dx<-3.5) {
						dx=3;
					}
					if(dy>3.5||dy<-3.5) {
						dy=3;
					}
					//lives are deducted only if the tracker is off
					if(!tracker) {
						livesKeeper-=1;
						}
					System.out.println("Bottom");
				}//end top wall and ball collision 
				
				//top wall collision
				if(collisions.ballTopWall(bounds)) {
					dy*=-1;
				}
				//ball and paddle general collision
				if(collisions.hitPaddleObject()) {
					//paddle center zone collision
					if(collisions.centerZoneCollision(paddle)) {
						dy*=-1.05;
						dx*=0.90;
						if(paddle.getWidth()<200) {
						paddle.setWidth(paddle.getWidth()*1.1);
						}
						//hitBox color turns green to indicate an accurate hit
						hitBox.setFill(Color.rgb(0,255,127));
						System.out.println("Center Zone");
					}
					//IFE: nested if else
					//paddle left zone collision
					else if(collisions.leftZoneCollision(paddle)) {
						dx*=1.1;
						dy*=-1.05;
						//hitBox color is set to red to indicate an inaccurate hit
						hitBox.setFill(Color.rgb(237, 67, 55));
						System.out.println("Left Zone");
					}
					//paddle right zone collision
					else if(collisions.rightZoneCollision(paddle)) {
						dx*=1.1;
						dy*=-1.05;
						//hitBox color is set to red to indicate an inaccurate hit
						hitBox.setFill(Color.rgb(237, 67, 55));
						System.out.println("Right Zone");
					}
					//the fill of the call is transfered to the paddle
					paddle.setFill(ball.getFill());
					//the stroke is set to the fill to prevent a visible outline around the paddle
					paddle.setStroke(paddle.getFill());
					
					//prevents the ball from getting stuck inside the paddle
					ball.setTranslateY((paddle.getHeight()-ball.getRadius()*2));
					
				}//end of ball and paddle general collisions
				
				//paddle and left boundary check
				if(collisions.paddleLeftWall()) {
					//the paddle is spawned on the right side of the screen
					paddle.setLayoutX(bounds.getMaxX());;
				}
				//paddle and right boundary check
				if(collisions.paddleRightWall(bounds)) {
					//the paddle is spawned on the left side of the screen
					paddle.setLayoutX(bounds.getMinX()-paddle.getWidth());
				}
				
				//ARR
				//loop created to count brick collisions according to the length of the array
				for(int i = 0; i<bricks.brickFieldArray.length;i++) {
				
				//check if the brick that is hit is still present on the pane
				if(pane.getChildren().contains((bricks.brickFieldArray[i]))) {
				//check if the brick on the pane was hit by the ball
				if(collisions.hitBrickCollision(bricks.brickFieldArray[i])) {
				
					//Check Collision Zone on Brick to determine delta x and y for the ball
					//Brick center zone
					if(collisions.brickCenterCollision(bricks.brickFieldArray[i])) {
						dx*=0.90;
						dy*=1.05;
						if(paddle.getWidth()<200) {
							paddle.setWidth(paddle.getWidth()*1.1);
						}
						System.out.println("Brick Center Zone");
					}
					//Brick left zone
					else if(collisions.brickLeftZoneCollision(bricks.brickFieldArray[i])) {
						dx*=1.08+Math.random();
						dy*=0.95;	
						
						System.out.println("Brick Left Zone");
					}
					//Brick right zone
					else if(collisions.brickLeftZoneCollision(bricks.brickFieldArray[i])) {
						dx*=1.08+Math.random();
						dy*=0.95;	
						System.out.println("Brick Right Zone");
					}
					
					//reverse the direction of delta y after collision
					dy*=-1;
					//remove the brick that was just hit
					pane.getChildren().remove(bricks.brickFieldArray[i]);
					
					//transfers the color of the brick to the ball
					ball.setFill(bricks.brickFieldArray[i].getFill());
					ball.setStroke(ball.getFill());
					
					//increment the score by one when a brick is removed
					scoreKeeper+=1;
					//decrease the width of the paddle by 8% when a brick is removed
					paddle.setWidth(paddle.getWidth()*.92);
					
					//created a speed boost effect if the new random number equals the random number 
					//initialized at the beginning of the animation timer
					int randomSpeedBoost = random.nextInt(10);
					System.out.println(randomSpeedBoost);
					if(randomSpeedBoost==randomSpeedMultiplier) {
						dx*=1.5;
						dy*=1.5;
					
					}
					
				}//end of brick collision loop
			}//end of brick in pane detection loop
		}//end of brick counter loop
				
				//*****End of Collision Detection*******
				
				
				//relocates the ball if it is found outside of the designated boundary
				if(ball.getLayoutX()-ball.getRadius()<=0) {
					ball.setLayoutX(ball.getRadius()+5);
				}
				else if(ball.getLayoutX()+ball.getRadius()>=600) {
					ball.setLayoutX(ball.getLayoutX()-ball.getRadius()-5);
				}
				
			
				
			}//end of handle method
    		
    	};//end AnimationTimer
    	//if reset is true, stop the timer and make the reset button visible
    	if(reset) {
    		menuButton.setVisible(false);
    		timer.stop();
    	}
    	timer.start();
    
    }
    
    public boolean tracker(boolean tracker) {
    	return tracker;
    }
    public void setTracker(boolean track) {
    	tracker = track;
    }
    
    
}
