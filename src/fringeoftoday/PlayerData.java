package fringeoftoday;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PlayerData {

	// Hash Map that keeps track of all the important data needed for the game
    private static Map<String, String> playerInfo = new HashMap<String, String>(); 

    /*
     * Set up the player file
     */
    public static void playerFileSetup() {
        File playerF = new File("../media/player.txt");
        if (!playerF.exists()) {
            try {
                // Make new file if one doesn't exist
                playerF.createNewFile();
            } catch (IOException e1) {
                System.out.println("Couldn't create file");
                e1.printStackTrace();
            }
            newFile();
        }
        readPlayerFile();
    }

    /*
     * Make a new file, in the case that one didn't exist or that one is remade
     */
    public static void newFile() {
        try {
            FileWriter fw = new FileWriter("../media/player.txt");
            fw.write("Coin:0,");
            fw.write("HPUpgrades:0,");
            fw.write("FireSpeedUpgrades:0,");
            fw.write("RangedUpgrades:0,");
            fw.write("SpeedUpgrades:0,");
            fw.write("PreviousRun:0,");
            fw.write("GOAT:0,");
            fw.write("Tutorial:0,");
            fw.write("Sounds:1,");
            fw.close();
        } catch (IOException e2) {
            System.out.println("No write");
            e2.printStackTrace();
        }

    }

    /*
     * Saving the file upon doing anything important
     */
    public static void writeFile() {
        try {
            // Fill it in with default values of 0
            FileWriter fw = new FileWriter("../media/player.txt");
            fw.write("Coin:" + playerInfo.get("Coin") + ",");
            fw.write("HPUpgrades:" + playerInfo.get("HPUpgrades") + ",");
            fw.write("FireSpeedUpgrades:" + playerInfo.get("FireSpeedUpgrades") + ",");
            fw.write("RangedUpgrades:" + playerInfo.get("RangedUpgrades") + ",");
            fw.write("SpeedUpgrades:" + playerInfo.get("SpeedUpgrades") + ",");
            fw.write("PreviousRun:" + playerInfo.get("PreviousRun") + ",");
            fw.write("GOAT:" + playerInfo.get("GOAT") + ",");
            fw.write("Tutorial:" + playerInfo.get("Tutorial") + ",");
            fw.write("Sounds:" + playerInfo.get("Sounds") + ",");
            fw.close();
        } catch (IOException e3) {
            System.out.println("No overwrite");
            e3.printStackTrace();
        }
    }

   /*
    * Read the player file to then be able to make the hash map with the values with
    */
    public static void readPlayerFile() {
        String text = null;
        try {
            Scanner sc = new Scanner(new File("../media/player.txt"));
            text = sc.useDelimiter("\\A").next();
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file");
            e.printStackTrace();
        }
        initalizeMap(text);
    }

    public static Map<String, String> getMap() {
        return playerInfo;
    }

    /*
     * Does the work needed to make the map, including assigning it values
     */
    private static void initalizeMap(String text) {
        String[] infoArr = text.split(":|,");
        for (int i = 0; i < infoArr.length; i = i + 2) {
            playerInfo.put(infoArr[i], infoArr[i + 1]);
        }
    }

    /*
     * easy thing to call in the work to make it update properly as to keep the format and righteousness of the file
     */
    public static void updateMap(String key, int i) {
        playerInfo.put(key, Integer.toString(i));

    }
}
