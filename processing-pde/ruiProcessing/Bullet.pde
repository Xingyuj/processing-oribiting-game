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