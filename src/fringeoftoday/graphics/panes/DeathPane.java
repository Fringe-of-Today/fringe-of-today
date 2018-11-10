package fringeoftoday.graphics.panes;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import fringeoftoday.graphics.GParagraph;
import fringeoftoday.MainApplication;

public class DeathPane extends GraphicsPane {
	private MainApplication program; 
	
	//TODO: connect variables to actual values
	private int level = -1;
	private int coins = -1;
	private String killer = "a frog";
	
	private GImage deathScreen; 
	private GParagraph deathMessage; 

	public DeathPane(MainApplication app) {
		this.program = app;
		deathScreen = new GImage("deathscreen.jpg",0,0);
		deathMessage = new GParagraph("Highest level: "+level+" Coins: "+coins+" Killed by: "+killer+"\n\nPress any key to continue", 0,0);
		deathMessage.setColor(Color.WHITE);
		deathMessage.setFont("Arial-24");
		deathMessage.move((MainApplication.WINDOW_WIDTH-deathMessage.getWidth())/2, 600);
	}

	@Override
	public void showContents() {
		program.add(deathScreen);
		program.add(deathMessage);
	}

	@Override
	public void hideContents() {
		program.remove(deathScreen);
		program.remove(deathMessage);
	}


	@Override
	public void keyPressed(KeyEvent e) {
		program.switchToMenu();
	}
}
