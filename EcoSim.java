/*
 * [EcoSim.java]
 * This program is a simulator for an ecosystem with varying populations of sheep and wolves.
 * Authour: Tara Rafi
 * December 12th, 2017
 */

import javax.swing.JOptionPane;

// import java.util.Scanner;
class EcoSim {
    public static void main(String[] args) {
 
 // Declare some variable values
 int startSheep, startWolves, startHealth, plantSpread;
 int dimension;
 int currentWolves, currentSheep, turn;
 int customize;
 int plantHealth = 0;
 Pokemon p;

 // Allow User to choose the terrain
 String[] options = {"Dirt", "Sand", "Grass", "Snow"};
 int terrain = JOptionPane.showOptionDialog(null, "Which terrain would you like to use?", "Terrain Customizer", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
 
 // Create option for the world values to be decided or chosen by default.
 customize = JOptionPane.showConfirmDialog(null, "Would you like to customize the world yourself?", "Cusomize or Use Default Values", JOptionPane.YES_NO_OPTION);
 if (customize == JOptionPane.YES_NO_OPTION) {
     dimension = Integer.parseInt( JOptionPane.showInputDialog(null, "What should the dimension of the field be?\n(10-30 Recommended)", "Customize World", JOptionPane.INFORMATION_MESSAGE));
     startSheep = Integer.parseInt( JOptionPane.showInputDialog(null, "How many sheep do you want to start with?", "Customize World", JOptionPane.INFORMATION_MESSAGE));
     startWolves = Integer.parseInt( JOptionPane.showInputDialog(null, "How many wolves do you want to start with?", "Customize World", JOptionPane.INFORMATION_MESSAGE));
     startHealth = Integer.parseInt( JOptionPane.showInputDialog(null, "What should be the starting health of all animals\n(50-100 Recommended)", "Customize World", JOptionPane.INFORMATION_MESSAGE));
     plantSpread = Integer.parseInt( JOptionPane.showInputDialog(null, "What should the plant spawn rate be?\n(1-3 Recommended)", "Customize World", JOptionPane.INFORMATION_MESSAGE));
 } else {
     dimension = 20;
     plantHealth = 20;
     startSheep = 20;
     startWolves = 6;
     startHealth = 60;
     plantSpread = 2;
 }
 
 // Create the map
 MapBoard map = new MapBoard(dimension, plantHealth);

 // Spawn initial sheep
 for (int i = 0; i < startSheep; i++) {
     p = new Sheep(startHealth);
     p.setMoved(false);
     map.chooseGender((Animal) p);
     map.choosePosition(p);
 }

 // Spawn initial wolves
 for (int i = 0; i < startWolves; i++) {
     p = new Wolf(startHealth);
     p.setMoved(false);
     map.chooseGender((Animal) p);
     map.choosePosition(p);
 }

 // Spawn initial plants
 for (int i = 0; i < plantSpread; i++) {
     map.spawnPlant();
 }

 // Create DisplayGrid to display 2D array
 DisplayGrid display = new DisplayGrid(map.getBoard());
 turn = 0;
 currentSheep = 0;
 currentWolves = 0;
 
 // Sets the terrain to what the user inputed
 display.setTerrain(terrain);

 // Counts the initial number of sheep and wolves on the board
 for (int i = 0; i < dimension; i++) {
     for (int j = 0; j < dimension; j++) {
  map.hasMoved(i, j);
  p = map.getPokemon(i, j);
  if (p == null) {
  } else if (p instanceof Sheep) {
   currentSheep++;
      } else if (p instanceof Wolf) {
   currentWolves++;
      }
     }
 }
 
 // Loop so the program keeps running
 do {
     try {
  Thread.sleep(300);
     } catch (Exception c) {
     }
     
     // Resets the values and increases the turn
     turn++;
     currentSheep = 0;
     currentWolves = 0;
     
     // Continues spawning plants
     for (int i = 0; i < plantSpread; i++) {
  map.spawnPlant();
     }
     
     // Makes the different pokemon do what they should
     for (int i = 0; i < dimension; i++) {
  for (int j = 0; j < dimension; j++) {
      p = map.getPokemon(i, j);
      if (p != null) {
   if (p instanceof Plant) {
       map.live(p);
   } else if (p instanceof Animal && !p.getMoved()) {
       map.chooseMove((Animal)p);
       map.live(p);
   }
      }
  }
     }
     
     // Counts the current number of sheep and wolves on the board, and allows animals to move
     for (int i = 0; i < dimension; i++) {
  for (int j = 0; j < dimension; j++) {
      map.hasMoved(i, j);
      p = map.getPokemon(i, j);
      if (p == null) {
      } else if (p instanceof Sheep) {
   currentSheep++;
      } else if (p instanceof Wolf) {
   currentWolves++;
      }
  }
     }
     
     // Refreshes the grid and prints number of wolves, sheep, and turns taken
     display.changeGrid(map.getBoard());
     display.refresh();
     System.out.println("Turn: " + turn);
     System.out.println("Current Sheep: " + currentSheep);
     System.out.println("Current Wolves: " + currentWolves);
     System.out.println();
     
     // Program ends when either wolves or sheep go extinct
 } while (currentSheep != 0 && currentWolves != 0);

    }
}