abstract class Animal extends Pokemon {
    private boolean gender;
    private boolean moved;

    Animal(int health) {
	super(health);
	moved = true;
    }

    Animal(int y, int x, int health, boolean gender) {
	super(y, x, health);
	this.gender = gender;
	moved = true;
    }

    public void setGender(boolean gender) {
	this.gender = gender;
    }

    public void setMoved(boolean moved) {
	this.moved = moved;
    }

    public boolean getGender() {
	return gender;
    }

    public boolean getMoved() {
	return moved;
    }
}

class Sheep extends Animal {
    Sheep(int health) {
	super(health);
    }

    Sheep(int y, int x, int health, boolean gender) {
	super(y, x, health, gender);
    }
}

class Wolf extends Animal/* implements Comparable*/{
    Wolf(int health) {
	super(health);
    }

    Wolf(int y, int x, int health, boolean gender) {
	super(y, x, health, gender);
    }
}