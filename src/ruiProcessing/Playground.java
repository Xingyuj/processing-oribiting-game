package ruiProcessing;

import processing.core.PApplet;

public class Playground extends PApplet {
	private int width;
	private int height;
	private Sun sun;
	private Planet[] planets;
	private int planetAmount = 5;
	private int planetInteval = 30;

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


		// for(int i=1;i<32;i++){
		// ellipse(width/2, height/2, circleSize/i, circleSize/i);
		// translate(width/2,height/2);
		// }

		// // int posX, posY = 0;
		// //
		// // int radiusX = 50;
		// // int radiusY = 100;
		// //
		// // float theta = 0;
		// //
		// // theta += 0.01;
		// //
		// // posX = (int) (radiusX * cos( theta ));
		// // posY = (int) (radiusY * sin( theta ));
		// //
		// // translate( width / 2, height / 2 );
		// // fill( 255 );
		// // ellipse( posX, posY, 5, 5 );
		//
		// float SunDiam = 80;
		//
		// float VenusDiam = 24;
		// float VenusOrbitRadius = 120;
		// float VenusAngle = 0;
		//
		// float EarthDiam = 30;
		// float EarthOrbitRadius = 200;
		// float EarthAngle = 0;
		//
		// float MoonDiam = 6;
		// float MoonOrbitRadius = 26;
		// float MoonAngle = 0;
		//
		// background(0,0,0); // inky blackness of space
		// translate(width/2,height/2); // move origin to the center of the
		// screen
		// noStroke();
		//
		// fill(255,200,64); // yellow-orange
		// ellipse(0,0,SunDiam,SunDiam); // the mighty Sun
		//
		// // save the solar system
		// // pushMatrix();
		//
		// // rotate Venus around the sun
		// rotate(VenusAngle);
		//
		// // move out to Venus orbit
		// translate(VenusOrbitRadius,0);
		//
		// fill(155,135,95);
		// ellipse(0,0,VenusDiam,VenusDiam);
		//
		// // rotate around the sun
		// rotate(EarthAngle);
		//
		// // move out to Earth orbit
		// translate(EarthOrbitRadius, 0);
		//
		// fill(64,64,255); // blue-ish
		// ellipse(0,0,EarthDiam,EarthDiam); // the noble Earth
		//
		// // rotate around the Earth
		// rotate(MoonAngle);
		//
		// // hove out to Moon orbit
		// translate(MoonOrbitRadius,0);
		//
		// fill(192,192,180); //grayish
		// ellipse(0,0,MoonDiam,MoonDiam); // the friendly Moon
		//
		// VenusAngle += 0.008;
		// EarthAngle += 0.005;
		// MoonAngle += 0.02;

	}

}
