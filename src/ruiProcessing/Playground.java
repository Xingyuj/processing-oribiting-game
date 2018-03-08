package ruiProcessing;

import processing.core.PApplet;

public class Playground extends PApplet {
	private int width;
	private int height;
	private Sun sun;
	private Planet[] planets;
	private int planetAmount = 5;
	private int planetInteval = 24;
	private Bullet bullet = new Bullet(this);
	private boolean hasBullet;

	public Playground() {
		this.width = 400;
		this.height = 400;
		this.sun = new Sun(25, 50, this);
		this.planets = new Planet[planetAmount];
		this.hasBullet = false;
	}

	private void spawnPlanets(int total) {
		for (int i = 0; i < planets.length; i++) {
			float r = 30;
			float d = (i + 1) * planetInteval;
			planets[i] = new Planet(r, d, this);
		}
	}

	public static void main(String[] args) {
		PApplet.main("ruiProcessing.Playground");
	}

	public void settings() {
		size(width, height);
	}

	public void setup() {
		background(0, 204, 255);
		this.spawnPlanets(planetAmount);
	}

	private boolean collisionDetection(float rx, float ry, float rw, float rh, double bx, double by, float d) {

		// first test the edges (this is necessary if the rectangle is larger
		// than the ball) - do this with the Pythagorean theorem

		// if ball entire width position is between rect L/R sides
		if (bx + d / 2 >= rx - rw / 2 && bx - d / 2 <= rx + rw / 2
				&& abs((float) (ry - by)) <= d / 2) {
			return true;
		}
		// if not, check if ball's entire height is between top/bottom of the
		// rect
		else if (by + d / 2 >= ry - rh / 2 && by - d / 2 <= ry + rh / 2
				&& abs((float) (rx - bx)) <= d / 2) {
			return true;
		}

		// if that doesn't return a hit, find closest corner
		// this is really just a point, so we can test if we've hit it
		// upper-left
		float xDist = (float) ((rx - rw / 2) - bx); // same as ball/ball, but first value
											// defines point, not center
		float yDist = (float) ((ry - rh / 2) - by);
		float shortestDist = sqrt((xDist * xDist) + (yDist * yDist));

		// upper-right
		xDist = (float) ((rx + rw / 2) - bx);
		yDist = (float) ((ry - rh / 2) - by);
		float distanceUR = sqrt((xDist * xDist) + (yDist * yDist));
		if (distanceUR < shortestDist) { // if this new distance is shorter...
			shortestDist = distanceUR; // ... update
		}

		// lower-right
		xDist = (float) ((rx + rw / 2) - bx);
		yDist = (float) ((ry + rh / 2) - by);
		float distanceLR = sqrt((xDist * xDist) + (yDist * yDist));
		if (distanceLR < shortestDist) {
			shortestDist = distanceLR;
		}

		// lower-left
		xDist = (float) ((rx - rw / 2) - bx);
		yDist = (float) ((ry + rh / 2) - by);
		float distanceLL = sqrt((xDist * xDist) + (yDist * yDist));
		if (distanceLL < shortestDist) {
			shortestDist = distanceLL;
		}

		// test for collision
		if (shortestDist < d / 2) { // if less than radius
			return true; // return true
		} else { // otherwise, return false
			return false;
		}
	}

	public void draw() {
		background(0, 204, 255);
		sun.show();

		if (keyPressed && keyCode == DOWN) {
			bullet.setStartLocation(sun.getPosition(), 40);
			hasBullet = true;
		}
		if (hasBullet) {
			bullet.update();
			bullet.show();
		}
		
		for (int i = 0; i < planets.length; i++) {
			planets[i].showOrbitCircle();
			if(hasBullet && this.collisionDetection(bullet.getX(), bullet.getY(), bullet.getW(), bullet.getH(), planets[i].getX(), planets[i].getY(), planets[i].getRadius()*2)){
				planets[i].setColor(500);;
			}
			planets[i].show();
		}

		
	}

}
