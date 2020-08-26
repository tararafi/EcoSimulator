abstract class Plant extends Pokemon {
    Plant(int healthGiven) {
	super(healthGiven);
    }

    Plant(int x, int y, int healthGiven) {
	super(x, y, healthGiven);
    }

}

class Bulbasaur extends Plant {
    Bulbasaur() {
	super(100);
    }

    Bulbasaur(int x, int y) {
	super(x, y, 100);
    }
}

class Oddish extends Plant {
    Oddish() {
	super(75);
    }

    Oddish(int x, int y) {
	super(x, y, 75);
    }
}

class Chikorita extends Plant {
    Chikorita() {
	super(40);
    }

    Chikorita(int x, int y) {
	super(x, y, 40);
    }
}

class Bellsprout extends Plant {
    Bellsprout() {
	super(30);
    }

    Bellsprout(int x, int y) {
	super(x, y, 30);
    }
}

class Budew extends Plant {
    Budew() {
	super(20);
    }

    Budew(int x, int y) {
	super(x, y, 20);
    }
}
