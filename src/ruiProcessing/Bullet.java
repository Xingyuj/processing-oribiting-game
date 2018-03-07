package ruiProcessing;

import processing.core.PApplet;

public class Bullet {
	private float x, y, w, h;
	private float speed;
	private float maxspeed;
	private boolean firing;
	private PApplet parent; // The parent PApplet that we will render ourselves onto

	
	public Bullet(PApplet pApplet) {
		this.parent = pApplet;
		this.maxspeed = 10;
		this.w = 2;
		this.h = 20;
		this.firing = false;
		this.speed = 0;
	}
	
	public void update() {
		if(firing == true){
			if(speed < maxspeed){
				speed += 0.5f;
			}
			y += speed;
			
			if (y > 400) {
				speed = 0;
				firing = false;
			}
		}
	}
	
	public void setStartLocation(float startX, float startY){
		x = startX;
		y = startY;
		this.firing = true;
	}
	
	public void show() {
    	parent.pushMatrix();
		parent.translate(x, y);
    	parent.rect(-w, -h/2, w, h);
//    	parent.fill(235, 252, 45);
    	parent.popMatrix();
	}
}
