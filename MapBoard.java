
class MapBoard {
    
    private Pokemon[][] board;
    private int dimension;
    int plantHealth;
    
    // List of constants
    public static final int REPRODUCTION_HEALTH_LOSS = 10;
    public static final int REPRODUCTION_MIN_HEALTH = 40;
    public static final int BABY_WOLF_HEALTH = 20;
    public static final int BABY_SHEEP_HEALTH = 20;
    public static final int EVOLUTION_AGE = 40;
    public static final int PLANT_DEFAULT_HEALTH = 40;
    public static final int CHANCE_TO_MOVE = 80;

    // Default constructor
    MapBoard() {
	board = new Pokemon[dimension][dimension];
	this.plantHealth = PLANT_DEFAULT_HEALTH;
	
    }

    // Specified constructor
    MapBoard(int dimension, int plantHealth) {
	board = new Pokemon[dimension][dimension];
	this.dimension = dimension;
	this.plantHealth = plantHealth;
    }

    // Getters for board and or pokemon
    public Pokemon[][] getBoard() {
	return board;
    }

    public Pokemon getPokemon(int y, int x) {
	return board[y][x];
    }

    public void live(Pokemon p) {
	p.changeLifeSpan(1);
	if (p instanceof Animal) {
	    int healthLost = -1 * (int) (p.getTurnsLived() / 100);
	    p.changeHealth(healthLost);
	    if (p.getHealth() < 1) {
		board[p.getY()][p.getX()] = null;
	    }
	}
    }

    public void chooseMove(Animal p) {
	if ((int)(Math.random() * 100) < CHANCE_TO_MOVE) {
	    directionMove(p);
	}
    }

    public void directionMove(Animal a) {
	int dir;
	boolean moved = false;
	
	// Wolves tend to move onto and consume sheep if possible
	if (a instanceof Wolf) {
	    if (a.getX() != 0) {
		if (board[a.getY()][a.getX() - 1] != null) {
		    if (board[a.getY()][a.getX() - 1] instanceof Sheep) {
			moved = true;
		    }
		}
	    }
	    if (a.getX() != dimension - 1 && !moved) {
		if (board[a.getY()][a.getX() + 1] != null) {
		    if (board[a.getY()][a.getX() + 1] instanceof Sheep) {
			moveAnimal(a, 1);
			moved = true;
		    }
		}
	    }
	    if (a.getY() != 0 && !moved) {
		if (board[a.getY() - 1][a.getX()] != null) {
		    if (board[a.getY() - 1][a.getX()] instanceof Sheep) {
			moveAnimal(a, 2);
			moved = true;
		    }
		}
	    }
	    if (a.getY() != dimension - 1 && !moved) {
		if (board[a.getY() + 1][a.getX()] != null) {
		    if (board[a.getY() + 1][a.getX()] instanceof Sheep) {
			moveAnimal(a, 3);
			moved = true;
		    }
		}
	    }
	}
	// Sheep tend to mate with other sheep
	if (a instanceof Sheep) {
	    if (a.getX() != 0) {
		if (board[a.getY()][a.getX() - 1] != null) {
		    if (board[a.getY()][a.getX() - 1] instanceof Sheep) {
			moveAnimal(a, 0); 
			moved = true;
		    }
		}
	    }
	    if (a.getX() != dimension - 1 && !moved) {
		if (board[a.getY()][a.getX() + 1] != null) {
		    if (board[a.getY()][a.getX() + 1] instanceof Sheep) {
			moveAnimal(a, 1);
			moved = true;
		    }
		}
	    }
	    if (a.getY() != 0 && !moved) {
		if (board[a.getY() - 1][a.getX()] != null) {
		    if (board[a.getY() - 1][a.getX()] instanceof Sheep) {
			moveAnimal(a, 2);
			moved = true;
		    }
		}
	    }
	    if (a.getY() != dimension - 1 && !moved) {
		if (board[a.getY() + 1][a.getX()] != null) {
		    if (board[a.getY() + 1][a.getX()] instanceof Sheep) {
			moveAnimal(a, 3);
			moved = true;
		    }
		}
	    }
	}
	// Sheep tend to eat food to gain health
	if (a instanceof Sheep) {
	    int maxNutrition = 0;
	    dir = -1;
	    if (a.getX() != 0 && board[a.getY()][a.getX() - 1] != null && board[a.getY()][a.getX() - 1] instanceof Plant && board[a.getY()][a.getX() - 1].getHealth() > maxNutrition) {
		maxNutrition = board[a.getY()][a.getX() - 1].getHealth();
		dir = 0;
		moved = true;
	    }
	    else if (a.getX() != dimension - 1 && board[a.getY()][a.getX() + 1] != null &&board[a.getY()][a.getX() + 1] instanceof Plant && board[a.getY()][a.getX() + 1].getHealth() > maxNutrition) {
		maxNutrition = board[a.getY()][a.getX() + 1].getHealth();
		dir = 1;
		moved = true;
	    }
	    else if (a.getY() != 0 && board[a.getY() - 1][a.getX()] != null && board[a.getY() - 1][a.getX()] instanceof Plant && board[a.getY() - 1][a.getX()].getHealth() > maxNutrition) {
		maxNutrition = board[a.getY() - 1][a.getX()].getHealth();
		dir = 2;
		moved = true;
	    }
	    
	    else if (a.getY() != dimension - 1 && board[a.getY() + 1][a.getX()] != null && board[a.getY() + 1][a.getX()] instanceof Plant && board[a.getY() + 1][a.getX()].getHealth() > maxNutrition) {
		maxNutrition = board[a.getY() + 1][a.getX()].getHealth();
		dir = 3;
		moved = true;
	    }
	    // Moves the sheep in the direction of the food
	    if (dir == -1) {
		moved = false;
	    } else {
		moveAnimal(a, dir);
		moved = true;
	    }
	}
	// Randomly moves sheep otherwise
	if (!moved) {
	    dir = (int)(Math.random() * 4);
	    moveAnimal(a, dir);
	}
    }

    // Sets boolean moved to true or false so that the animal does not move more than once per turn
    public void hasMoved(int y, int x) {
	if (board[y][x] == null) {
	} else if (board[y][x] instanceof Animal) {
	    board[y][x].setMoved(false);
	}
    }

    // Places pokemon down on board
    public void addPokemon(Pokemon p) {
	if (board[p.getY()][p.getX()] == null) {
	    board[p.getY()][p.getX()] = p;
	} else if (board[p.getY()][p.getX()] instanceof Plant && !(p instanceof Plant)) {
	    // Sheep that spawn on plants consume them
	    if (p instanceof Sheep) {
		p.changeHealth(board[p.getY()][p.getX()].getHealth());
		board[p.getY()][p.getX()] = p;
		// Wolves that spawn on plants destroy them
	    } else {
		board[p.getY()][p.getX()] = p;
	    }
	    // Keeps trying spaces to spawn the pokemon in different spaces
	} else if (!(p instanceof Plant)) {
	    boolean spaceAvailable = false;
	    for (int i = 0; i < dimension; i++) {
		for (int j = 0; j < dimension; j++) {
		    if (board[i][j] == null) {
			spaceAvailable = true;
		    }
		}
	    }
	    if (spaceAvailable) {
		choosePosition(p);
	    }
	}
    }

    // Chooses the type of plant to spawn
    public void spawnPlant() {
	Plant plantType;
	int spawnChooser = (int)(Math.random() * 100);
	if (spawnChooser > 80) {
	    plantType = new Bulbasaur();
	    choosePosition(plantType);
	} else if (spawnChooser > 60) {
	    plantType = new Oddish();
	    choosePosition(plantType);
	} else if (spawnChooser > 40) {
	    plantType = new Chikorita();
	    choosePosition(plantType);
	} else if (spawnChooser > 20) {
	    plantType = new Bellsprout();
	    choosePosition(plantType);
	} else {
	    plantType = new Budew();
	    choosePosition(plantType);
	}
    }

    // Randomly chooses place to spawn.
    public void choosePosition(Pokemon p) {
	p.setY((int)(Math.random() * dimension));
	p.setX((int)(Math.random() * dimension));
	addPokemon(p);
    }

    // Randomly chooses gender
    public void chooseGender(Animal a) {
	int random = (int)(Math.random() * 2);
	if (random == 0) {
	    a.setGender(true);
	} else {
	    a.setGender(false);
	}
    }

    // Employs the interface movable
    public void changeCoordinates(Animal a, int dir) {
	if (dir == 0) {
	    a.moveLeft();
	} else if (dir == 1) {
	    a.moveRight();
	} else if (dir == 2) {
	    a.moveUp();
	} else if (dir == 3) {
	    a.moveDown();
	}
    }

    public void moveAnimal(Animal a, int dir) {

	boolean canMove = false;
	int x, y;
	
	// Ensures that it does not go out of bounds
	if (dir == 0 && a.getX() != 0) {
	    x = a.getX() - 1;
	    y = a.getY();
	    canMove = true;
	} else if (dir == 1 && a.getX() != dimension - 1) {
	    x = a.getX() + 1;
	    y = a.getY();
	    canMove = true;
	} else if (dir == 2 && a.getY() != 0) {
	    x = a.getX();
	    y = a.getY() - 1;
	    canMove = true;
	} else if (dir == 3 && a.getY() != dimension - 1) {
	    x = a.getX();
	    y = a.getY() + 1;
	    canMove = true;
	} else {
	    x = -1;
	    y = -1;
	} 

	if (canMove) {
	    // If nothing is there it moves onto the position
	    if (board[y][x] == null) {
		board[a.getY()][a.getX()] = null;
		changeCoordinates(a, dir);
		board[y][x] = a;
		
		// Plants get consumed by sheep, wolves simply destroy it
	    } else if (board[y][x] instanceof Plant) {
		board[a.getY()][a.getX()] = null;
		changeCoordinates(a, dir);
		if (a instanceof Sheep) {
		    a.changeHealth(board[a.getY()][a.getX()].getHealth());
		    board[y][x] = a;
		} else {
		    board[y][x] = a;
		}
		
		// Sheep avoid wolves
	    } else if (board[y][x] instanceof Wolf && a instanceof Sheep) {
		if (a.getX() != 0 && dir != 0) {
		    try {
			if (!(board[a.getY()][a.getX() - 1] instanceof Wolf)) {
			    moveAnimal(a, 0); // Moves left
			}
		    } catch (NullPointerException e) {
			moveAnimal(a, 0);
		    }
		}
		if (a.getX() != dimension - 1 && dir != 1) {
		    try {
			if (!(board[a.getY()][a.getX() + 1] instanceof Wolf)) {
			    moveAnimal(a, 1); // Moves right
			}
		    } catch (NullPointerException e) {
			moveAnimal(a, 1);
		    }
		}
		if (a.getY() != 0 && dir != 2) {
		    try {
			if (!(board[a.getY() - 1][a.getX()] instanceof Wolf)) {
			    moveAnimal(a, 2); // Moves up
			}
		    } catch (NullPointerException e) {
			moveAnimal(a, 2);
		    }
		}
		if (a.getY() != dimension - 1 && dir != 3) {
		    try {
			if (!(board[a.getY() + 1][a.getX()] instanceof Wolf)) {
			    moveAnimal(a, 3); // Moves down
			}
		    } catch (NullPointerException e) {
			moveAnimal(a, 3);
		    }
		}
	    } else if (board[y][x] instanceof Sheep && a instanceof Wolf) {
		board[a.getY()][a.getX()] = null;
		changeCoordinates(a, dir);
		a.changeHealth(board[a.getY()][a.getX()].getHealth());
		board[y][x] = a;
	    } else if (board[y][x] instanceof Animal && a instanceof Animal && (a.getGender() != board[y][x].getGender())) {
		if (a instanceof Sheep && board[y][x] instanceof Sheep) {
		    while (board[y][x].getHealth() > REPRODUCTION_MIN_HEALTH && a.getHealth() > REPRODUCTION_MIN_HEALTH) {
			Sheep babySheep = new Sheep(BABY_SHEEP_HEALTH);
			chooseGender(babySheep);
			choosePosition(babySheep);
			board[y][x].changeHealth(-1 * REPRODUCTION_HEALTH_LOSS);
			a.changeHealth(-1 * REPRODUCTION_HEALTH_LOSS);
		    }
		} else if (a instanceof Wolf) {
		    if (board[y][x].getHealth() > REPRODUCTION_MIN_HEALTH && a.getHealth() > REPRODUCTION_MIN_HEALTH && a.getTurnsLived() > EVOLUTION_AGE) {
			Wolf baby = new Wolf(BABY_WOLF_HEALTH);
			chooseGender(baby);
			choosePosition(baby);
			board[y][x].changeHealth(-1 * REPRODUCTION_HEALTH_LOSS);
			a.changeHealth(-1 * REPRODUCTION_HEALTH_LOSS);
		    }
		}
	    } else if (board[y][x] instanceof Wolf && a instanceof Wolf) {
		if (board[y][x].getHealth() > a.getHealth()) {
		    a.changeHealth(-1 * REPRODUCTION_HEALTH_LOSS);
		} else if (board[y][x].getHealth() < a.getHealth()) {
		    board[y][x].changeHealth(-1 * REPRODUCTION_HEALTH_LOSS);
		    if (board[y][x].getHealth() < 1) {
			board[a.getY()][a.getX()] = null;
			changeCoordinates(a, dir);
			board[a.getY()][a.getX()] = a;
		    }
		} else {
		    int random = (int)(Math.random() * 2);
		    if (random == 0) {
			a.changeHealth(-1 * REPRODUCTION_HEALTH_LOSS);
		    } else {
			board[y][x].changeHealth(-1 * REPRODUCTION_HEALTH_LOSS);
			if (board[y][x].getHealth() < 1) {
			    board[a.getY()][a.getX()] = null;
			    board[a.getY()][a.getX()] = a;
			}
		    }
		}
	    }
	}
    }
}