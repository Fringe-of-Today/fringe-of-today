package fringeoftoday.graphics.panes;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import fringeoftoday.MainApplication;
import fringeoftoday.graphics.GButton;

public class ShopPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	public static final int BUTTON_WIDTH = 200;
	public static final int BUTTON_HEIGHT = 100;
	public static final int MAX_UPGRADES = 10;

	private GLabel title;
	private GButton btnBack;
	private GLine headerSeparator;
	private GLabel coinCtr;
	private GLine horizSeparator;
	private GLine vertSeperator;

	private GButton hpBtn;
	private GButton meleeBtn;
	private GButton rangedBtn;
	private GButton speedBtn;

	public ShopPane(MainApplication app) {
		this.program = app;

		// Title for the shop
		title = new GLabel("Shop", MainApplication.WINDOW_WIDTH / 2 - 50, MainApplication.WINDOW_HEIGHT / 18);
		title.setFont("Arial-46");

		// Back button
		btnBack = new GButton("Back", 0, 0, MainApplication.WINDOW_WIDTH / 13, MainApplication.WINDOW_HEIGHT / 12);

		// Header separator
		headerSeparator = new GLine(0, MainApplication.WINDOW_HEIGHT / 11, MainApplication.WINDOW_WIDTH,
				MainApplication.WINDOW_HEIGHT / 11);

		// Coin counter at the top left
		coinCtr = new GLabel("Coin: " + MainApplication.getMap().get("Coin"), MainApplication.WINDOW_WIDTH - 300,
				MainApplication.WINDOW_HEIGHT / 18);
		coinCtr.setFont("Arial-46");

		// Horizontal Separator
		horizSeparator = new GLine(MainApplication.WINDOW_WIDTH / 2, MainApplication.WINDOW_HEIGHT / 11,
				MainApplication.WINDOW_WIDTH / 2, MainApplication.WINDOW_HEIGHT);

		// Vertical Separator
		vertSeperator = new GLine(0, 12 * MainApplication.WINDOW_HEIGHT / 22, MainApplication.WINDOW_WIDTH,
				12 * MainApplication.WINDOW_HEIGHT / 22);

		// HP Upgrade Button
		int hpCost = (Integer.parseInt(MainApplication.getMap().get("HPUpgrades")) + 1) * 10;
		hpBtn = new GButton("Cost: " + hpCost, MainApplication.WINDOW_WIDTH / 4 - BUTTON_WIDTH / 2,
				12 * MainApplication.WINDOW_HEIGHT / 22 - BUTTON_HEIGHT - 10, BUTTON_WIDTH, BUTTON_HEIGHT);

		// Melee Damage Upgrade Button
		int meleeCost = (Integer.parseInt(MainApplication.getMap().get("MeleeUpgrades")) + 1) * 10;
		meleeBtn = new GButton("Cost: " + meleeCost, 3 * MainApplication.WINDOW_WIDTH / 4 - BUTTON_WIDTH / 2,
				12 * MainApplication.WINDOW_HEIGHT / 22 - BUTTON_HEIGHT - 10, BUTTON_WIDTH, BUTTON_HEIGHT);

		// Ranged Damage Upgrade Button
		int rangedCost = (Integer.parseInt(MainApplication.getMap().get("RangedUpgrades")) + 1) * 10;
		rangedBtn = new GButton("Cost: " + rangedCost, MainApplication.WINDOW_WIDTH / 4 - BUTTON_WIDTH / 2,
				MainApplication.WINDOW_HEIGHT - BUTTON_HEIGHT - 10, BUTTON_WIDTH, BUTTON_HEIGHT);

		// Speed Movement Upgrade Button
		int speedCost = (Integer.parseInt(MainApplication.getMap().get("SpeedUpgrades")) + 1) * 10;
		speedBtn = new GButton("Cost: " + speedCost, 3 * MainApplication.WINDOW_WIDTH / 4 - BUTTON_WIDTH / 2,
				MainApplication.WINDOW_HEIGHT - BUTTON_HEIGHT - 10, BUTTON_WIDTH, BUTTON_HEIGHT);
	}

	@Override
	public void showContents() {
		program.add(title);
		program.add(btnBack);
		program.add(headerSeparator);
		program.add(coinCtr);
		program.add(horizSeparator);
		program.add(vertSeperator);
		program.add(hpBtn);
		program.add(meleeBtn);
		program.add(rangedBtn);
		program.add(speedBtn);
	}

	@Override
	public void hideContents() {
		program.remove(title);
		program.remove(btnBack);
		program.remove(headerSeparator);
		program.remove(coinCtr);
		program.remove(horizSeparator);
		program.remove(vertSeperator);
		program.remove(hpBtn);
		program.remove(meleeBtn);
		program.remove(rangedBtn);
		program.remove(speedBtn);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == btnBack) {
			program.switchToMenu();
		}
	}
}
