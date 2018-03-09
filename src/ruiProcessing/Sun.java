package ruiProcessing;

import processing.core.PApplet;

public class Sun {
	private int diameter;
	private int glowDiameter;
	private int position;
	private boolean fired;
	private int trajectoryHead;
	private int trajectoryRear;
	private PApplet parent; // The parent PApplet that we will render ourselves onto
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Sun(int diameter, int glowDiameter, PApplet pApplet) {
		this.diameter = diameter;
		this.glowDiameter = glowDiameter;
		this.parent = pApplet;
		this.position = 200;
	}
	
	public void fireSunWrath() {
		if(parent.keyPressed && parent.keyCode == parent.DOWN){
			trajectoryHead = 50;
			trajectoryRear = 40;
			fired = true;
		}
		while(fired && trajectoryRear < 400){
			parent.stroke(153);
			parent.line(this.position, trajectoryRear, this.position, trajectoryHead);
			trajectoryRear += 5;
			trajectoryHead += 5;
		}
		fired = false;
	}
	
	public void move(int steps){
		this.position += steps;
	}
	
	public void show() {
    	parent.pushMatrix();
		parent.translate(position, 0);
    	parent.noStroke();
		parent.ellipseMode(parent.CENTER);
    	parent.fill(255, 230, 180); 
		parent.ellipse(0, glowDiameter/2, glowDiameter, glowDiameter);
    	parent.fill(255, 255, 100);
    	parent.ellipse(0, glowDiameter/2, diameter, diameter);
    	parent.popMatrix();
	}
}
