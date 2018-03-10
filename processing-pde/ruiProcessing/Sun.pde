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