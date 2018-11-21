package fringeoftoday.graphics.panes;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.io.File;

import acm.graphics.GObject;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import fringeoftoday.MainApplication;
import fringeoftoday.PlayerData;
import fringeoftoday.audio.AudioPlayer;
import fringeoftoday.graphics.GButton;
import fringeoftoday.graphics.panes.GraphicsPane;
import starter.GButtonMD;

public class MenuPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	public static final int BUTTON_WIDTH = MainApplication.BUTTON_WIDTH;
	public static final int BUTTON_HEIGHT = MainApplication.BUTTON_HEIGHT;
	
	private Font btnFont = new Font("PKMN Mystery Dungeon", 0, 80);
	
	private GButtonMD btnPlay;
	private GButtonMD btnShop;
	private GButtonMD btnExit;
	private GButtonMD btnTutorial;
	private GImage title;
	private GImage btnAudio;
	private GLabel lastRun;
	private GLabel bestRun;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		loadFont();
		// Title banner - maybe use GImage instead?
		title = new GImage("../media/logo_transparent.png", (MainApplication.WINDOW_WIDTH - 600) / 2, 30);
		title.setSize(600, 300);
		
		// Play button
		btnPlay = new GButtonMD("Play", (MainApplication.WINDOW_WIDTH - BUTTON_WIDTH) / 2, 400, BUTTON_WIDTH,
				BUTTON_HEIGHT, "blue");
		btnPlay.getLabel().setFont(btnFont);
		
		// Shop button
		btnShop = new GButtonMD("Shop", (MainApplication.WINDOW_WIDTH - BUTTON_WIDTH) / 2, 550, BUTTON_WIDTH,
				BUTTON_HEIGHT, "green");
		btnShop.getLabel().setFont(btnFont);
		
		// Exit button
		btnExit = new GButtonMD("Exit", (MainApplication.WINDOW_WIDTH - BUTTON_WIDTH) / 2, 700, BUTTON_WIDTH,
				BUTTON_HEIGHT, "green");
		btnExit.getLabel().setFont(btnFont);
		
		//Tutorial button
		btnTutorial = new GButtonMD("?", 0, 0, 100, 100);
		btnTutorial.getLabel().setFont(btnFont);
		
		//Audio button
		btnAudio = new GImage("../media/soundon.jpg", MainApplication.WINDOW_WIDTH - BUTTON_HEIGHT, 0);
		btnAudio.setSize(BUTTON_HEIGHT,BUTTON_HEIGHT);
		
		// Latest Score
		lastRun = new GLabel("On your last run, you got to floor: " + PlayerData.getMap().get("PreviousRun"),
				MainApplication.WINDOW_WIDTH - 310, MainApplication.WINDOW_HEIGHT - 40);
		lastRun.setFont(new Font("PKMN Mystery Dungeon", 0, 30));
		
		// Best Score
		bestRun = new GLabel("On your best run, you got to floor: " + PlayerData.getMap().get("GOAT"),
				MainApplication.WINDOW_WIDTH - 310, MainApplication.WINDOW_HEIGHT - 15);
		bestRun.setFont(new Font("PKMN Mystery Dungeon", 0, 30));
	}

	@Override
	public void showContents() {
		program.add(title);
		program.add(btnPlay);
		program.add(btnShop);
		program.add(btnExit);
		program.add(btnTutorial);
		program.add(btnAudio);
		program.add(lastRun);
		program.add(bestRun);
	}

	@Override
	public void hideContents() {
		program.remove(title);
		program.remove(btnPlay);
		program.remove(btnShop);
		program.remove(btnExit);
		program.remove(btnTutorial);
		program.remove(btnAudio);
		program.remove(lastRun);
		program.remove(bestRun);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == btnPlay) {
			program.switchToGame();
		} else if (obj == btnShop) {
			program.switchToShop();
		} else if (obj == btnExit) {
			program.exitProgram();
		} else if (obj == btnTutorial) {
			program.switchToTutorial();
		} else if (obj == btnAudio) {
			AudioPlayer audio = AudioPlayer.getInstance();
			if (MainApplication.isSoundOn) {
				audio.stopSound("sounds", "menumusic.mp3");
				btnAudio.setImage("soundoff.jpg");
				btnAudio.setSize(BUTTON_HEIGHT,BUTTON_HEIGHT);
				MainApplication.isSoundOn = false;
			}
			else {
				audio.playSound("sounds", "menumusic.mp3");
				btnAudio.setImage("soundon.jpg");
				btnAudio.setSize(BUTTON_HEIGHT,BUTTON_HEIGHT);
				MainApplication.isSoundOn = true;
			}
		}
		
		
	}
}
