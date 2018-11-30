package fringeoftoday;

import fringeoftoday.audio.AudioPlayer;
import fringeoftoday.entities.Entity;
import fringeoftoday.entities.EntityManager;
import fringeoftoday.floor.FloorManager;
import fringeoftoday.graphics.GraphicsApplication;
import fringeoftoday.graphics.panes.DeathPane;
import fringeoftoday.graphics.panes.GamePane;
import fringeoftoday.graphics.panes.MenuPane;
import fringeoftoday.graphics.panes.ShopPane;
import fringeoftoday.graphics.panes.TutorialPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainApplication extends GraphicsApplication {
    public static final int WINDOW_WIDTH = 1600;
    public static final int WINDOW_HEIGHT = 900;
    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 100;

    public static final String MUSIC_FOLDER = "sounds";
    public static final String MUSIC_MENU = "menumusic.mp3";

    private ShopPane shopPane;
    private MenuPane menu;
    private TutorialPane tutorial;
    private GamePane game;
    private DeathPane deathPane;
    private FloorManager floorManager;
    private EntityManager entityManager;

    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    public static void importAllLayouts() {
        importLayoutsByType(LayoutType.FLOOR);
        // System.out.println();
        importLayoutsByType(LayoutType.STANDARD);
        // System.out.println();
        importLayoutsByType(LayoutType.BOSS);
        // System.out.println();
        importLayoutsByType(LayoutType.SPAWN);

    }

    public static void importer(String fileLocation, int numRows, int numCols) {
        String text = null;
        File file = new File("../media/layouts/" + fileLocation + ".txt");
        char textArr[][] = new char[numRows][numCols];

        Scanner sc;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                for (int row = 0; row < numRows; row++) {
                    text = sc.nextLine();
                    String[] textChars = text.split(" ", numCols);
                    for (int col = 0; col < numCols; col++) {
                        // System.out.print(textChars[col]);
                        textArr[row][col] = textChars[col].charAt(0);
                    }
                    // System.out.println();
                }
                // FloorManager.printLayout(textArr, numRows, numCols, fileLocation);
                addLayout(fileLocation, textArr);
                if (sc.hasNextLine()) {
                    sc.nextLine();
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error doing this");
            e.printStackTrace();
        }
    }

    public static void addLayout(String fileLocation, char[][] textArr) {
        // Souts for demoing
        // System.out.println("\nAdding something...");
        switch (fileLocation) {
            case ("floors"):
                // FloorManager.printLayout(textArr, FloorManager.FLOOR_ROWS, FloorManager.FLOOR_COLS, fileLocation);
                FloorManager.addFloorLayout(textArr);
                break;
            case ("rooms_standard"):
                // FloorManager.printLayout(textArr, FloorManager.ROOM_ROWS, FloorManager.ROOM_COLS, fileLocation);
                FloorManager.addRoomLayout(textArr);
                break;
            case ("rooms_spawn"):
                // FloorManager.printLayout(textArr, FloorManager.ROOM_ROWS, FloorManager.ROOM_COLS, fileLocation);
                FloorManager.setSpawnRoom(textArr);
                break;
            case ("rooms_boss"):
                // FloorManager.printLayout(textArr, FloorManager.ROOM_ROWS, FloorManager.ROOM_COLS, fileLocation);
                FloorManager.addBossRoomLayout(textArr);
                break;
        }

    }

    public static void importLayoutsByType(LayoutType type) {
        switch (type) {
            case FLOOR:
                importer("floors", FloorManager.FLOOR_ROWS, FloorManager.FLOOR_COLS);
                break;

            case STANDARD:
                importer("rooms_standard", FloorManager.ROOM_ROWS, FloorManager.ROOM_COLS);
                break;

            case BOSS:
                importer("rooms_boss", FloorManager.ROOM_ROWS, FloorManager.ROOM_COLS);
                break;

            case SPAWN:
                importer("rooms_spawn", FloorManager.ROOM_ROWS, FloorManager.ROOM_COLS);
                break;
        }
    }

    public void init() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        PlayerData.playerFileSetup();
        floorManager = new FloorManager();
        entityManager = new EntityManager();
        importAllLayouts();
        FloorManager.generateNewFloor();
    }

    public void run() {
        tutorial = new TutorialPane(this);
        shopPane = new ShopPane(this);
        menu = new MenuPane(this);
        game = new GamePane(this);
        deathPane = new DeathPane(this);

        switchToMenu();
    }

    public FloorManager getFloorManager() {
        return floorManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void switchToMenu() {
        if (Integer.parseInt(PlayerData.getMap().get("Sounds")) == 1) {
            AudioPlayer audio = AudioPlayer.getInstance();
            audio.playSound(MUSIC_FOLDER, MUSIC_MENU, true);
        }
        switchToScreen(menu);
    }

    public void switchToShop() {
        switchToScreen(shopPane);
    }

    public void switchToTutorial() {
        PlayerData.updateMap("Tutorial", Integer.parseInt(PlayerData.getMap().get("Tutorial")) + 100);
        switchToScreen(tutorial);
    }

    public void switchToGame() {
        AudioPlayer audio = AudioPlayer.getInstance();
        audio.stopSound(MUSIC_FOLDER, MUSIC_MENU);
        PlayerData.writeFile();
        if (Integer.parseInt(PlayerData.getMap().get("Tutorial")) == 0) {
            PlayerData.updateMap("Tutorial", 1);
            switchToTutorial();
        } else {
            switchToScreen(game);
        }

    }

    public void switchToDeath(Entity killer) {
        deathPane.setKiller(killer);
        switchToScreen(deathPane);
    }

    public void exitProgram() {
        PlayerData.writeFile();
        System.exit(0);
    }
    
    

    private enum LayoutType {
        FLOOR, STANDARD, BOSS, SPAWN
    }
}
