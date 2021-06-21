/*
 * @author: Sevan Golnazarian
 * CollisionTool contains methods for checking bounds for all game objects
 */
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CollisionTool {
	//CON
	public CollisionTool(double minX, double maxX, Circle c, Rectangle r) {
		this.minX = minX;
		this.maxX = maxX;
		this.c = c;
		this.r = r;
		System.out.println("CollisionTool.java");
	}
	
	
	private double minX;
	private double maxX;
	
	
	Circle c;
	Rectangle r;
	
	//ball and wall collisions
	 public boolean ballLeftRightWall() {
	    	return (((c.getLayoutX()-c.getRadius()-5)<=minX)||
	    			(c.getLayoutX()+c.getRadius()+5>=maxX));
	    }
	//ball and top boundary
	 public boolean ballBottomWall(Bounds bounds) {
	    	return (c.getLayoutY()>=(bounds.getMaxY()-c.getRadius()));
	    }
	 //ball and bottom boundary
	 public boolean ballTopWall(Bounds bounds) {
		 return (c.getLayoutY()<=(bounds.getMinY()+c.getRadius()));
	 }
	 
	 //paddle left and right wall boundary check
	 //the amount the paddle is allowed to pass through the wall is equivalent to its width
	 //it is then respawned on the opposite side adjusted for its width
	 public boolean paddleLeftWall() {
	    	return (r.getLayoutX()<-r.getWidth());
	    }
	    public boolean paddleRightWall(Bounds bounds) {
	    	return(r.getLayoutX()>600);
	    }
	    
	  //ball and paddle collision detection 
	    public boolean hitPaddleObject() {
	    	//return (c.intersects(c.sceneToLocal(rectObject.localToScene(rectObject.getBoundsInLocal()))));
	    	return (c.getBoundsInParent().intersects((r.getBoundsInParent())));
	   
	    }
	    
	    //ball and brick collision
	    public boolean hitBrickCollision(Rectangle brick) {
	    	return (c.getBoundsInParent().intersects(brick.getBoundsInParent()));
	    }
	 
	    
	    //ball and paddle center, left, and right zone collisions
	    public boolean centerZoneCollision(Rectangle paddle) {
	    	return (c.getLayoutX()+c.getRadius()>paddle.getLayoutX()+(paddle.getWidth()/3)&&
					c.getLayoutX()+c.getRadius()<(paddle.getLayoutX()+((paddle.getWidth()/3)*2)));
	    }
	  public boolean leftZoneCollision(Rectangle paddle) {
		  return (c.getLayoutX()+c.getRadius()<((paddle.getWidth()/3)+paddle.getLayoutX()));
	  }
	  public boolean rightZoneCollision(Rectangle paddle) {
		  return (c.getLayoutX()+c.getRadius()>((paddle.getWidth()/3)*2)+paddle.getLayoutX());
	  }
	  
	  //ball and paddle center, left, right zone collisions
	  public boolean brickCenterCollision(Rectangle brick) {
	    	return (c.getLayoutX()+c.getRadius()>brick.getX()+(brick.getWidth()/3)&&
					c.getLayoutX()+c.getRadius()<(brick.getX()+((brick.getWidth()/3)*2)));
	    }
	  public boolean brickLeftZoneCollision(Rectangle brick) {
		  return (c.getLayoutX()+c.getRadius()<((brick.getWidth()/3)+brick.getX()));
	  }
	  public boolean brickRightZoneCollision(Rectangle brick) {
		  return (c.getLayoutX()+c.getRadius()>((brick.getWidth()/3)*2)+brick.getX());
	  }

}
