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