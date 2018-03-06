package ruiProcessing;

import processing.core.PApplet;

public class Sun {
	private int diameter;
	private int glowDiameter;
	private int position;
	private PApplet parent; // The parent PApplet that we will render ourselves onto

	
	public Sun(int diameter, int glowDiameter, PApplet pApplet) {
		this.diameter = diameter;
		this.glowDiameter = glowDiameter;
		this.parent = pApplet;
		this.position = 200;
	}
	
	public void show() {
    	parent.pushMatrix();
		parent.noStroke();
		parent.ellipseMode(parent.CENTER);  // Set ellipseMode to RADIUS
		parent.ellipse(position, glowDiameter/2, glowDiameter, glowDiameter);  // Draw white ellipse using RADIUS mode
    	parent.fill(235, 252, 45);
    	parent.ellipse(position, glowDiameter/2, diameter, diameter);  // Draw gray ellipse using CENTER mode
    	parent.fill(246, 255, 158);  // Set fill to white
    	parent.popMatrix();
	}
}
