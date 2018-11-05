package fringeoftoday.floor;

import java.util.*;

/**
 * Holds data on a single room
 * 
 * @author Jacob Shour
 *
 */
public class Room {
	public static final int ROOM_ROWS = 11;
	public static final int ROOM_COLS = 17;

	private Space roomLayout[][];
	private ArrayList<Exit> exits;

	public Room() {
		roomLayout = new Space[ROOM_ROWS][ROOM_COLS];
		exits = new ArrayList<Exit>();
	}

	public Space getSpace(int row, int col) {
		return roomLayout[row][col];
	}

	public ArrayList<Exit> getExits() {
		return exits;
	}

	/**
	 * Sets the space at the given X/Y to the given space
	 * 
	 * @param row - X coordinate in the room
	 * @param col - Y coordinate in the room
	 * @param r   - Space to insert
	 */
	public void setSpace(int row, int col, SpaceType type) {
		roomLayout[row][col] = new Space(row, col, type);
	}

	/**
	 * Adds the given exit to the list of exits thr room has
	 * 
	 * @param e - Exit to add
	 */
	public void addExit(Exit e) {
		exits.add(e);
	}
}
