package ruiProcessing;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PConstants;

public class Planet{
	private int radius;
	private float angle;
	private float distance;
	private PApplet parent; // The parent PApplet that we will render ourselves onto
	private float orbitSpeed;
	
	public Planet(float radius, float distance, PApplet pApplet) {
		this.radius = new Random().nextInt(12) % 5 + 10;
		this.distance = distance;
		this.angle = pApplet.random(PConstants.TWO_PI);
//		this.orbitSpeed = new Random().nextFloat() % 0.14f + 0.11f;
		this.orbitSpeed = pApplet.random(0.01f, 0.09f);
		this.parent = pApplet;
	}
	
	private void orbit(){
		this.angle += this.orbitSpeed;
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
//    	parent.fill(235, 252, 45);
//    	parent.ellipse(0, 0, radius*2, radius*2);
//    	parent.pushMatrix();
    	parent.fill(255, 100);
    	parent.rotate(angle);
    	parent.translate(distance, 0);
    	parent.ellipse(0, 0, radius*2, radius*2);
    	parent.popMatrix();

    	this.orbit();
//        if (parent.planets != null) {
//          for (int i = 0; i < planets.length; i++) {
//            planets[i].show();
//          }
//        }
//        popMatrix();
    }
}
