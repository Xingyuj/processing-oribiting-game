  int width;
  int height;
  Sun sun;
  ArrayList<Planet> planets;
  ArrayList<Planet> planetsRemoved;
  int planetAmount = 5;
  int planetInteval = 24;
  Bullet bullet = new Bullet();
  int flames;
  int hits;

  void spawnPlanets(int total) {
    for (int i = 0; i < total; i++) {
      float d = (i + 1) * planetInteval;
      planets.add(new Planet(d));
    }
  }
  
  void setup() {
    size(400, 400);

    sun = new Sun(25, 50, this);
    planets = new ArrayList<Planet>();
    planetsRemoved = new ArrayList<Planet>();
    flames = 5;
    hits = 0;
    spawnPlanets(planetAmount);  
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
  
  public void displayScoreboard(){
    pushMatrix();
    textSize(25);
    fill(0, 102, 153);
    text("Flames:" + flames, 10, 30); 
    text("Hit:" + hits, 10, 60);
    popMatrix();
  }
  
  public void displayGameover(){
    pushMatrix();
    textSize(20);
    text("Game is Over", 100, 150); 
    fill(0, 102, 153);
    text("Number of Flames left: " + flames, 100, 180);
    text("Number of planets left: " + (5 - hits), 100, 210);
    text("Number of Hits: " + hits, 100, 240);
    popMatrix();
  }
  
  private void drawEarth(){
    pushMatrix();
    fill(51, 102, 253);
    arc(200, 400, 400, 80, PI, TWO_PI, OPEN);
    popMatrix();
  }

  public void draw() {
    background(0, 204, 255);
    if(flames <= 0 && !bullet.isFiring() || hits >= 5){
      displayGameover();
    } else {
      displayScoreboard();
      if (keyPressed && keyCode == LEFT && sun.getPosition() > 25) {
        sun.move(-1);
      } else if (keyPressed && keyCode == RIGHT && sun.getPosition() < 375){
        sun.move(1);
      }
      sun.show();

      if (!bullet.isFiring() && keyPressed && keyCode == DOWN) {
        bullet.setStartLocation(sun.getPosition(), 40);
        flames--;
      }
      if (bullet.isFiring()) {
        bullet.update();
        bullet.show();
      }
      
      for (Planet planet : planets) {
        planet.showOrbitCircle();
        if(!planetsRemoved.contains(planet)){
          if(bullet.isFiring() && this.collisionDetection(bullet.getX(), bullet.getY(), bullet.getW(), bullet.getH(), planet.getX(), planet.getY(), planet.getRadius()*2)){
            if(!planet.isGotHit()){
              planet.setGotHit(true);
              hits++;
            } else if (planet.getLifeTime() <= 0) {
              planetsRemoved.add(planet);
              continue;
            }
          }
          planet.show();
        }
      }
      drawEarth();
    }
  }
  
class Bullet {
  float x, y, w, h;
  float speed;
  float maxspeed;
  boolean firing;

  
  public Bullet() {
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
      
      if (y > 420) {
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
      pushMatrix();
      translate(x, y);
      rect(-w, -h/2, w, h);
      popMatrix();
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public float getW() {
    return w;
  }

  public void setW(float w) {
    this.w = w;
  }

  public float getH() {
    return h;
  }

  public void setH(float h) {
    this.h = h;
  }

  public boolean isFiring() {
    return firing;
  }

  public void setFiring(boolean firing) {
    this.firing = firing;
  }
  
}

class Planet {
  double x, y;
  float radius;
  float angle;
  float distance;
  float orbitSpeed;
  int[] color1 = new int[3];
  boolean gotHit;
  int lifeTime;
  int direction;

  Planet(float distance) {
    this.radius = random(11.0f, 8.0f);
    this.distance = distance;
    this.angle = random(PConstants.TWO_PI);
    this.orbitSpeed = random(0.01f, 0.09f);
    this.color1 = new int[]{(int) random(255), (int) random(255), (int) random(255)};
    this.gotHit = false;
    this.lifeTime = 10;
    this.direction = (int) random(2) * 2 - 1;
  }

  private void orbit() {
    this.angle += direction * this.orbitSpeed;
    this.x = 200 + Math.cos(angle) * this.distance;
    this.y = 200 + Math.sin(angle) * this.distance;
  }

  public void showOrbitCircle() {
    pushMatrix();
    stroke(0, 255, 255);
    noFill();
    translate(200, 200);
    ellipse(0, 0, distance * 2, distance * 2);
    popMatrix();
  }

  public void show() {
    pushMatrix();
    noStroke();
    translate(200, 200);
    if (gotHit) {
      if (lifeTime > 0){
        fill(255,0,0);
        lifeTime--;
      } else {
        popMatrix();
        return;
      }
    } else {
      fill(color1[0], color1[1], color1[2]);
    }
    rotate(angle);
    translate(distance, 0);
    ellipse(0, 0, radius * 2, radius * 2);
    this.orbit();
    popMatrix();
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

  public boolean isGotHit() {
    return gotHit;
  }

  public void setGotHit(boolean gotHit) {
    this.gotHit = gotHit;
  }

  public int getLifeTime() {
    return lifeTime;
  }

  public void setLifeTime(int lifeTime) {
    this.lifeTime = lifeTime;
  }
  
}

class Sun {
  private int diameter;
  private int glowDiameter;
  private int position;
  private boolean fired;
  private int trajectoryHead;
  private int trajectoryRear;
  
  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public Sun(int diameter, int glowDiameter, PApplet pApplet) {
    this.diameter = diameter;
    this.glowDiameter = glowDiameter;
    this.position = 200;
  }
  
  public void fireSunWrath() {
    if(keyPressed && keyCode == DOWN){
      trajectoryHead = 50;
      trajectoryRear = 40;
      fired = true;
    }
    while(fired && trajectoryRear < 400){
      stroke(153);
      line(this.position, trajectoryRear, this.position, trajectoryHead);
      trajectoryRear += 5;
      trajectoryHead += 5;
    }
    fired = false;
  }
  
  public void move(int steps){
    this.position += steps;
  }
  
  public void show() {
      pushMatrix();
      translate(position, 0);
      noStroke();
      ellipseMode(CENTER);
      fill(255, 230, 180); 
      ellipse(0, glowDiameter/2, glowDiameter, glowDiameter);
      fill(255, 255, 100);
      ellipse(0, glowDiameter/2, diameter, diameter);
      popMatrix();
  }
}