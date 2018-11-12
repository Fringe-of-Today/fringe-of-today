package fringeoftoday.graphics.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GObject;
import acm.graphics.GImage;
import acm.graphics.GRect;
import acm.graphics.GLabel;
import fringeoftoday.MainApplication;
import fringeoftoday.floor.FloorManager;
import fringeoftoday.graphics.GButton;
import fringeoftoday.graphics.GParagraph;
import fringeoftoday.graphics.panes.GraphicsPane;

public class GamePane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	public static final int BUTTON_WIDTH = MainApplication.BUTTON_WIDTH;
	public static final int BUTTON_HEIGHT = MainApplication.BUTTON_HEIGHT;
	public static final int HEADER_WIDTH = MainApplication.WINDOW_WIDTH/3;
	public static final int HEADER_HEIGHT = 196;
	public static final int SPACE_SIZE = (MainApplication.WINDOW_HEIGHT - HEADER_HEIGHT)/FloorManager.ROOM_ROWS;
	public static final String FILE_PATH = "../media/textures/";
	private int level = -1;
	private int mDamage = -1;//Check variable names/change for consistency
	private int rDamage = -1;
	private int moveSpeed = -1;
	private GButton btnDie; //Debug, remove when done
	private GRect minimapBox; //Minimap, left header
	private GRect infoBox; //Center header
	private GParagraph infoText;//Center header content
	private GRect healthBox; //Right header
	private GImage[][] room;
	
	public GamePane(MainApplication app) {
		super();
		program = app;
		
		//HEADER
		minimapBox = new GRect(0, 0, HEADER_WIDTH, HEADER_HEIGHT);
		
		infoBox = new GRect(HEADER_WIDTH,0,HEADER_WIDTH,HEADER_HEIGHT);
		infoText = new GParagraph(
				"Level: "+level+"\nMelee Damage: "+mDamage+"\nRanged Damage: "+rDamage+"\nMove Speed: "+moveSpeed,0,0);
		
		infoText.setFont("Arial-24");
		infoText.move(infoBox.getX()+(infoBox.getWidth()-infoText.getWidth())/2, (infoBox.getY()+infoText.getHeight())/2);
		
		healthBox = new GRect(HEADER_WIDTH*2,0,HEADER_WIDTH,HEADER_HEIGHT);
		
		//FIELD
		
		//OTHER
		btnDie = new GButton("DIE", (MainApplication.WINDOW_WIDTH - BUTTON_WIDTH) / 2, (MainApplication.WINDOW_HEIGHT - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
	}

	@Override
	public void showContents() {//split showContents into showHeader and showField for clarity
		showHeader(); //Top bar
		showField(); //Game field
		program.add(btnDie);//Testing death screen, remove when things are added
	}

	@Override
	public void hideContents() {
		removeHeader();
		removeField();
		program.remove(btnDie);//Testing death screen, remove when things are added
	}
	
	public void showHeader() {
		program.add(minimapBox);
		program.add(infoBox);
		program.add(infoText);
		program.add(healthBox);
	}
	
	public void removeHeader() {
		program.remove(minimapBox);
		program.remove(infoBox);
		program.remove(infoText);
		program.remove(healthBox);
	}
	
	public void showField() {
		
	}
	
	public void removeField() {
		
	}
	
	public void createImageList() {
		int rows = FloorManager.ROOM_ROWS;
		int cols = FloorManager.ROOM_COLS;
		
		room = new GImage[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				room[i][j] = new GImage(getImagePath(i, j), (j * SPACE_SIZE) + HEADER_HEIGHT, (i * SPACE_SIZE));
			}
		}
	}
	
	public String getImagePath(int row, int col) {
		return null;
	}
	
	public void onDeath() {//Trigger this when player is dead, should add other functions - tally score, etc.
		program.switchToDeath();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == btnDie) {
			onDeath();
		}
	}
}
