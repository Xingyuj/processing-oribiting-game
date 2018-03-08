package ruiProcessing;

import processing.core.PApplet;
import processing.core.PConstants;

public class Planet{
	private double x, y;
	private float radius;
	private float angle;
	private float distance;
	private PApplet parent; // The parent PApplet that we will render ourselves onto
	private float orbitSpeed;
	private int color;
	
	public Planet(float radius, float distance, PApplet pApplet) {
		this.radius = pApplet.random(11.0f, 8.0f);
		this.distance = distance;
		this.angle = pApplet.random(PConstants.TWO_PI);
		this.orbitSpeed = pApplet.random(0.01f, 0.09f);
		this.parent = pApplet;
		this.color = 100;
	}
	
	private void orbit(){
		this.angle += this.orbitSpeed;
		this.x = 200 + Math.cos(angle) * this.distance;
		this.y = 200 + Math.sin(angle) * this.distance;
	}
	
	public void showOrbitCircle(){
    	parent.pushMatrix();
    	parent.stroke(1);
    	parent.noFill();
    	parent.translate(200, 200);
    	parent.ellipse(0, 0, distance*2, distance*2);
    	parent.popMatrix();

	}
	
    public void show(){
    	parent.pushMatrix();
    	parent.noStroke();
    	parent.translate(200, 200);
    	parent.fill(color);

    	parent.rotate(angle);
    	parent.translate(distance, 0);
    	parent.ellipse(0, 0, radius*2, radius*2);
    	parent.popMatrix();
    	this.orbit();
    }

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
