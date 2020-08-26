abstract class Pokemon implements Moveable {
    private int health, startHealth;
    private int x, y;
    private int turnsLived;

    Pokemon(int x, int y, int health) {
	this.health = health;
	this.startHealth = health;
	this.x = x;
	this.y = y;
	this.turnsLived = 0;
    }

    Pokemon(int health) {
	this.health = health;
	this.turnsLived = 0;
    }

    public int getHealth() {
	return health;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public int getTurnsLived() {
	return turnsLived;
    }

    public int getStartHealth() {
	return startHealth;
    }

    public void changeHealth(int health) {
	this.health += health;
    }

    public void setHealth(int health) {
	this.health = health;
    }

    public void setX(int x) {
	this.x = x;
    }

    public void setY(int y) {
	this.y = y;
    }

    public void changeLifeSpan(int lifeSpan) {
	this.turnsLived += lifeSpan;
    }

    public void moveLeft() {
	x--;
    }

    public void moveRight() {
	x++;
    }

    public void moveUp() {
	y--;
    }

    public void moveDown() {
	y++;
    }

    public boolean getGender() {
	return true;
    }

    public boolean getMoved() {
	return true;
    }

    public void setGender(boolean gender) {

    }

    public void setMoved(boolean moved) {

    }
}