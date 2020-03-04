/**
 * @author: Agnes Xu
 * @date: 2020/3/4
 * @version: 1.0
 */

package byog.Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.awt.Font;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import edu.princeton.cs.introcs.StdDraw;

public class Game {

    /// static final variables
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private static final int ENTRYX = 40;
    private static final int ENTRYY = 5;
    private static final String NORTH = "w";
    private static final String EAST = "d";
    private static final String SOUTH = "s";
    private static final String WEST = "a";
    private static final String PATH = "saved.txt";
    public static final int welcomeBoardX = 300;
    public static final int welcomeBoardY = 400;
    Font font1 = new Font("Times New Roman", Font.BOLD, 50);
    Font font2 = new Font("Times New Roman", Font.PLAIN, 30);

    /// Instance members
    private boolean setupMode = true;     // flag to check whether setup has been done
    private boolean newGameMode = false; // flag to check whether a new game is gonna be generated
    private boolean quitMode = false; // flag to check whether a game is supposed to be quited
    private String seedString = ""; // store input random seed numbers as String

    private TETile[][] world;
    private int playerX;
    private int playerY;

    private void switchSetupMode() {
        setupMode = !setupMode;
    }

    private void switchNewGameMode() {
        newGameMode = !newGameMode;
    }

    private void switchQuitMode() {
        quitMode = !quitMode;
    }

    /* Processes game recursively according to a given input Strings */
    private void processInput(String input) {

        if (input == null) {
            System.out.println("No input given.");
            System.exit(0);
        }

        String first = Character.toString(input.charAt(0));
        first = first.toLowerCase(); // normalize an input to lower case
        processInputString(first);

        if (input.length() > 1) {
            String rest = input.substring(1);
            processInput(rest); // recursive call until input ends
        }

    }
    /* Processes game according to a given single input String */
    private void processInputString(String first) {

        if (setupMode) {      // when the setup hasn't been done
            switch (first) {
                case "n":       // new game gonna be generated
                    switchNewGameMode();
                    break;
                case "s":       // setup a new game
                    setupNewGame();
                    break;
                case "l":       // load the previously saved game
                    load();
                    break;
                case "q":
                    System.exit(0);
                    break;
                default:        // append next seed integer to seedString
                    try {
                        Long.parseLong(first);
                        seedString += first;
                    } catch (NumberFormatException e) { // exit program if input is invalid
                        System.out.println("Invalid input given: " + first);
                        System.exit(0);
                    }
                    break;
            }
        } else {                // when the setup has been done
            switch (first) {
                // @Note: Add my keyboard preferences
                case NORTH:
                case "o":
                case EAST:
                case "l":
                case SOUTH:
                case "n":
                case WEST:
                case "k":
                    move(first);
                    break;
                case ":":
                    switchQuitMode();
                    break;
                case "q":
                    saveAndQuit();
                    System.exit(0);
                    break;
                default:
            }
        }

    }

    /**
     * Display welcome msg
     */
    private void processWelcome() {

        // prepare welcome board window
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(welcomeBoardX, welcomeBoardY);
        StdDraw.clear(StdDraw.PINK);
    }

    /**
     * Display welcome message on welcome board
     */
    public void DisplayWelcome(){

        /** initialize the canvas */
        StdDraw.clear(StdDraw.PINK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(font1);
        StdDraw.text(0.5, 0.8, "Welcome to the random world!");

        StdDraw.setFont(font2);
        StdDraw.text(0.5, 0.5, "New Game: N");
        StdDraw.text(0.5, 0.475, "Load Game: L");
        StdDraw.text(0.5, 0.45, "Quit: Q");

    }


    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }

}
