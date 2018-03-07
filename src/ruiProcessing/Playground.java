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


	public Playground() {
		this.width = 400;
		this.height = 400;
		this.sun = new Sun(25, 50, this);
		this.planets = new Planet[planetAmount];
	}

	private void spawnPlanets(int total) {
		for (int i = 0; i < planets.length; i++) {
			float r = 30;
			float d = (i + 1) * planetInteval;
			planets[i] = new Planet(r, d, this);
			// if (level < 3) {
			// int num = int(random(0,4));
			// planets[i].spawnMoons(num, level+1);
			// }
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

	public void draw() {
		background(0, 204, 255);

		sun.show();

		for (int i = 0; i < planets.length; i++) {
			planets[i].showOrbitCircle();
			planets[i].show();
		}
		
		if(keyPressed && keyCode == DOWN){
			bullet.setStartLocation(sun.getPosition(), 40);
		}
		bullet.update();
		bullet.show();
		
	}

}
