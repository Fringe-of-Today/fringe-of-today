package fringeoftoday.graphics.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GObject;
import acm.graphics.GImage;
import acm.graphics.GRect;
import acm.graphics.GLabel;
import fringeoftoday.MainApplication;
import fringeoftoday.graphics.GButton;
import fringeoftoday.graphics.panes.GraphicsPane;

public class GamePane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	public static final int BUTTON_WIDTH = 200;
	public static final int BUTTON_HEIGHT = 100;
	public static final int HEADER_WIDTH = MainApplication.WINDOW_WIDTH/3;
	public static final int HEADER_HEIGHT = 200;
	private GButton btnDie;
	private GRect minimapBox;
	private GRect infoBox;
	private GRect healthBox;
	
	public GamePane(MainApplication app) {
		super();
		program = app;
		minimapBox = new GRect(0, 0, HEADER_WIDTH, HEADER_HEIGHT);
		infoBox = new GRect(HEADER_WIDTH,0,HEADER_WIDTH,HEADER_HEIGHT);
		healthBox = new GRect(HEADER_WIDTH*2,0,HEADER_WIDTH,HEADER_HEIGHT);
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
		program.add(healthBox);
	}
	
	public void removeHeader() {
		program.remove(minimapBox);
		program.remove(infoBox);
		program.remove(healthBox);
	}
	
	public void showField() {
		
	}
	
	public void removeField() {
		
	}
	
	public void onDeath() {
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
