
/* [DisplayGrid.java] 
 * A Small program for Display a 2D String Array graphically
 * @author Mangat
 */


import javax.swing.*;
import java.awt.*;

class DisplayGrid {

    private JFrame frame;
    private int maxX, maxY, GridToScreenRatio;
    private int terrain;
    private Pokemon[][] world;

    DisplayGrid(Pokemon[][] w) {
	this.world = w;
	this.terrain = 2;
	maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
	maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
	GridToScreenRatio = maxY / (world.length + 1);

	System.out.println("Map size: " + world.length + " by " + world[0].length + "\nScreen size: " + maxX + "x"
		+ maxY + " Ratio: " + GridToScreenRatio);

	this.frame = new JFrame("Map of World");

	GridAreaPanel worldPanel = new GridAreaPanel();

	frame.getContentPane().add(BorderLayout.CENTER, worldPanel);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	frame.setVisible(true);
    }

    public void changeGrid(Pokemon[][] w) {
	this.world = w;
    }

    public void refresh() {
	frame.repaint();
    }
    
    public void setTerrain(int terrain) {
	this.terrain = terrain;
    }
    
    class GridAreaPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
	    super.repaint();
	    setDoubleBuffered(true);
	    Image background = Toolkit.getDefaultToolkit().getImage("./images/Grass.png");
	    
	    if (terrain == 0) {
		background = Toolkit.getDefaultToolkit().getImage("./images/Dirt.jpg");
	    } else if (terrain == 1) {
		background  = Toolkit.getDefaultToolkit().getImage("./images/Sand.png");
	    } else if (terrain == 2) {
    	    	background = Toolkit.getDefaultToolkit().getImage("./images/Grass.png");
    	    } else if (terrain == 3) {
		background = Toolkit.getDefaultToolkit().getImage("./images/Snow.png");
	    }
	    
	    Image sheep = Toolkit.getDefaultToolkit().getImage("./images/Sheep.png");
	    Image wolf = Toolkit.getDefaultToolkit().getImage("./images/Wolf.png");
	    Image wolfFemale = Toolkit.getDefaultToolkit().getImage("./images/WolfFemale.png");
	    Image babySheep = Toolkit.getDefaultToolkit().getImage("./images/BabySheep.png");
	    Image babyWolf = Toolkit.getDefaultToolkit().getImage("./images/BabyWolf.png");
	    Image babyWolfFemale = Toolkit.getDefaultToolkit().getImage("./images/BabyWolfFemale.png");
	    Image bulbasaur = Toolkit.getDefaultToolkit().getImage("./images/Bulbasaur.png");
	    Image chikorita = Toolkit.getDefaultToolkit().getImage("./images/Chikorita.png");
	    Image oddish = Toolkit.getDefaultToolkit().getImage("./images/Oddish.png");
	    Image bellsprout = Toolkit.getDefaultToolkit().getImage("./images/Bellsprout.png");
	    Image budew = Toolkit.getDefaultToolkit().getImage("./images/Budew.png");

	    for (int i = 0; i < world[0].length; i++) {
		for (int j = 0; j < world.length; j++) {
		    g.drawImage(background, i * GridToScreenRatio, j * GridToScreenRatio, GridToScreenRatio,
			    GridToScreenRatio, this);
		}
	    }
	    for (int i = 0; i < world[0].length; i++) {
		for (int j = 0; j < world.length; j++) {
		    if (world[i][j] == null) {
			g.drawImage(background, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio,
				GridToScreenRatio, this);
		    } else if (world[i][j] instanceof Bulbasaur) {
			g.drawImage(bulbasaur, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio,
				GridToScreenRatio, this);
		    } else if (world[i][j] instanceof Chikorita) {
			g.drawImage(chikorita, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio,
				GridToScreenRatio, this);
		    } else if (world[i][j] instanceof Oddish) {
			g.drawImage(oddish, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio,
				GridToScreenRatio, this);
		    } else if (world[i][j] instanceof Bellsprout) {
			g.drawImage(bellsprout, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio,
				GridToScreenRatio, this);
		    } else if (world[i][j] instanceof Plant) {
			g.drawImage(budew, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio,
				GridToScreenRatio, this);
		    } else if (world[i][j] instanceof Wolf) {
			if (world[i][j].getTurnsLived() < 30) {
			    if (world[i][j].getGender()) {
				g.drawImage(babyWolf, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio,
					GridToScreenRatio, this);
			    } else {
				g.drawImage(babyWolfFemale, j * GridToScreenRatio, i * GridToScreenRatio,
					GridToScreenRatio, GridToScreenRatio, this);
			    }
			} else if (world[i][j].getGender()) {
			    g.drawImage(wolf, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio,
				    GridToScreenRatio, this);
			} else {
			    g.drawImage(wolfFemale, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio,
				    GridToScreenRatio, this);
			}
		    } else if (world[i][j] instanceof Sheep) {
			if (world[i][j].getTurnsLived() < 30) {
			    g.drawImage(babySheep, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio,
				    GridToScreenRatio, this);
			} else {
			    g.drawImage(sheep, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio,
				    GridToScreenRatio, this);
			}
		    }

		}
	    }
	}
    }
}
