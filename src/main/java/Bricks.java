/*
 *FIV: Field instance variables and methods both getter, setter and others
 *VIS: private and public variables and methods
 *OTH: other methods e.i. brickField which creates an array of rectangles
 *ARR: an array of rectangles
 *CON and OVL
 *OTH: "this" keyword used in constructors and other methods
 */

import java.security.SecureRandom;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//FIV
//VIS
public class Bricks extends Rectangle {
		//brick row count can be modified via the constructor
		private int brickRowCount;
	    private final int brickColumnCount = 5;
	    //brick width is 5/32 of the containers pref width
	    private double brickWidth;
	    //Brick height is about 1/16 of the containers pref height
	    private double brickHeight;
	    //padding is ~1/48 of containers pref width
	    private double brickPadding;
	    //brickOffsetTop is equal to brick offset left (from constructor)
	    private double brickOffsetTop;
	    //brickOffsetLeft is equal to brick width
	    private double brickOffsetLeft = 30;
	    private int brickFieldArrayaySize;
	    
	    //brickFieldArray
	    public Rectangle [] brickFieldArray;
	    
	    //random is used to generate random colors for each brick element of type rectangle in the brickFieldArray
	    private static final SecureRandom random = new SecureRandom();
	   
	    //CON: parameter constructor
	    //OVL
	    public Bricks(Pane pane, int rows) {	
	    	//
	    	this.brickWidth=((pane.getPrefWidth()*brickColumnCount/31));
	    	//
	    	//this.brickHeight=(pane.getPrefHeight()*0.0625);
	    	this.brickHeight=(pane.getPrefHeight()*0.075);
	    	//
	    	//this.brickPadding = pane.getPrefWidth()*0.0208;
	    	//
	    	this.brickPadding = pane.getPrefWidth()*0.03;
	    	//
	    	//this.brickOffsetLeft=35;
	    	this.brickOffsetLeft = this.brickPadding;
	    	this.brickOffsetTop=30;
	    	this.brickRowCount = rows;
	    	this.brickFieldArrayaySize = brickRowCount*brickColumnCount;
	    	brickFieldArray=new Rectangle[brickFieldArrayaySize];   
	    	System.out.println("Bricks.java");
	    	//this.setFocusTraversable(false);
} 
	    //CON: parameter constructor
	    //OVL
		  public Bricks(int rows) {
			  this.brickRowCount = rows;
			  this.brickWidth = 96;
			  this.brickHeight = 37.5;
			  this.brickPadding = 12.5;
			  this.brickOffsetLeft = 35;
			  this.brickOffsetTop = 30;
			  this.brickFieldArrayaySize = brickRowCount*brickColumnCount;
			  brickFieldArray = new Rectangle[brickFieldArrayaySize];
		  }
	  //VIS
	  //CON: default constructor
	  public Bricks() {
		  this.brickRowCount = 3;
		  this.brickWidth = 96;
		  this.brickHeight = 37.5;
		  this.brickPadding = 12.5;
		  this.brickOffsetLeft = 35;
		  this.brickOffsetTop = 30;
		  this.brickFieldArrayaySize = brickRowCount*brickColumnCount;
		  brickFieldArray = new Rectangle[brickFieldArrayaySize];
	  }
	 
	  //OTH: this keyword is used
	  //ARR: the brick field is created from the brickFieldArrayay using nested for loops
	  //LPS
	    public void brickField(Pane pane) {
	    	int i = 0;
	    	for(int c = 0;c<brickColumnCount;c++) {
	    		for(int r = 0; r<brickRowCount;r++) {
	    			
	    			Rectangle rec = new Rectangle();
	    	    	 rec.setWidth(brickWidth); 
	    	    	 rec.setHeight(brickHeight);
	    			
	    	    	 double brickX = (c*(brickWidth+brickPadding))+brickOffsetLeft;
	    	    	 
	    	    	double brickY= (r*(brickHeight+brickPadding))+brickOffsetTop;
	    	    	
	    			rec.setX(brickX);
	    			rec.setY(brickY);
	    			//rec.setStroke(Color.GREY);
	    			rec.setFill(Color.rgb(random.nextInt(255), random.nextInt(255),
	        				random.nextInt(255),1));
	    			this.brickFieldArray[i]=rec;
	    			i++;
	    		}
	    }
    }  
	    //VIS
	    //OTH: method prints a message
	    public void drawSingleBrick(Pane pane, int i) {
	    	pane.getChildren().add(brickFieldArray[i]);
	    	System.out.println("I'm a single brick!");
	    }
	    //SET: setter that use the "this" keyword
	    public void setBrickRowCount(int rows) {
	    	this.brickRowCount = rows;
	    }
	    //GET
		public int getBrickRowCount() {
			return brickRowCount;
		}
		//GET
		public int getBrickColumnCount() {
			return brickColumnCount;
		}
		//GET
		public double getBrickWidth() {
			return brickWidth;
		}
		//SET: setter that use the "this" keyword
		public void setBrickWidth(int brickWidth) {
			this.brickWidth = brickWidth;
		}
		//GET
		public double getBrickHeight() {
			return brickHeight;
		}
		//SET: setter that use the "this" keyword
		public void setBrickHeight(int brickHeight) {
			this.brickHeight = brickHeight;
		}
		//GET
		public double getBrickPadding() {
			return brickPadding;
		}
		//SET: setter that use the "this" keyword
		public void setBrickPadding(int brickPadding) {
			this.brickPadding = brickPadding;
		}
		//GET
		public double getBrickOffsetTop() {
			return brickOffsetTop;
		}
		//SET: setter that use the "this" keyword
		public void setBrickOffsetTop(int brickOffsetTop) {
			this.brickOffsetTop = brickOffsetTop;
		}
		//GET
		public double getBrickOffsetLeft() {
			return brickOffsetLeft;
		}
		//SET: setter that use the "this" keyword
		public void setBrickOffsetLeft(int brickOffsetLeft) {
			this.brickOffsetLeft = brickOffsetLeft;
		}
		
}
