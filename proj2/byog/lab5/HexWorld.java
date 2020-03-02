package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.text.Position;
import java.util.Random;


/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 60;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random();

    public static class Position {
        int x;
        int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position(Position p) {
            this.x = p.x;
            this.y = p.y;
        }

        public boolean isSamePosition(Position p) {
            return p.x == this.x && p.y == this.y;
        }
    }

    /**
     * Return the width of the specific row
     * @param size -> size of the hexagonal
     * @param i -> row index (i = 0 for the bottom row)
     */
    public static  int hexRowWidth(int size, int i){
        int j = i;
        if(j >= size){
            j = size * 2 - 1 - j; // it's symmetric
        }
        int width = size + j * 2;
        return width;
    }

    /**
     * Calculate the leftmost x offset of each row,
     * considering the (size - 1)th row starts at 0
     *   xxxx
     *  xxxxxx
     * xxxxxxxx
     * xxxxxxxx <-- i = 2, starts from the -2
     *  xxxxxx <-- i = 1, starts from the -1
     *   xxxx <-- i = 0, starts from the 0
     * @param size -> size of the hexagonal
     * @param row -> row index (i = 0 for the bottom row)
     * @param corner -> current position
     * @return
     */

    public static Position getStartPointOfRow(int size, int row, Position corner) {
        // calculate x
        int factor = row;
        if (row >= size) {
            factor = 2 * size - 1 - row;
        }
        int x = corner.x - factor;
        // calculate y
        int y = corner.y + row;
        return new Position(x, y);
    }

    /**
     * Draw one row to the world
     * @param world -> the canvas to draw on
     * @param p -> leftmost position
     * @param width -> width of this row
     * @param t -> pattern
     */
    public static void addrow(TETile[][] world, Position p, int width, TETile t){
        for(int xi = 0; xi < width; xi += 1){
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = t;
        }
    }

    /**
     * Draw the hexagonal for specified size on world
     * @param p ->  leftmost position of each row
     * @param world -> the canvas to draw on
     * @param size -> size of hexagonal
     * @param t -> pattern
     */
    public static void drawHexagonal(Position p, TETile[][] world, int size, TETile t){
        if (size < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        for(int yi = 0; yi < 2 * size; yi += 1){
            int width = hexRowWidth(size, yi);
            Position startPoint = getStartPointOfRow(size, yi, p);
            addrow(world, startPoint, width, t);
        }
    }
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.GRASS;
            default:
                return Tileset.MOUNTAIN;
        }
    }

    public static void main(String[] args){
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        int size = 3;
        Position p = new Position(10, 30);
        drawRandomVerticalHexes(world, 3, p, size);
        getNewTopPos(p, size);
        //System.out.println("--------------------");
        drawRandomVerticalHexes(world, 4, p, size);
        getNewTopPos(p, size);
        //System.out.println("--------------------");
        drawRandomVerticalHexes(world, 5, p, size);
        getNewBottomPos(p, size);
        //System.out.println("--------------------");
        drawRandomVerticalHexes(world, 4, p, size);
        getNewBottomPos(p, size);
        //System.out.println("--------------------");
        drawRandomVerticalHexes(world, 3, p, size);
        // draws the world to the screen
        ter.renderFrame(world);
    }

    /**
     * get the top leftmost pos
     *
     *        ****
     *       ******
     *      ********
     *  xxxx********
     * xxxxxx******
     *xxxxxxxx**** <- new leftmost starting pos
     *xxxxxxxx
     * xxxxxx
     *  xxxx
     * @param p
     * @param size
     */
    private static void getNewTopPos(Position p, int size){
        p.x = p.x + 2 * size - 1;
        p.y += size;
    }

    /** get the bottom leftmost pos
     *            xxxx
     *           xxxxxx
     *          xxxxxxxx
     *          xxxxxxxx****
     *           xxxxxx******
     *            xxxx********
     *                ********
     *                 ******
     * new return pos ->****
     * @param p
     * @param size
     */
    private static void getNewBottomPos(Position p, int size){
        p.x = p.x + 2 * size - 1;
        p.y -= size;
    }

    private static void drawRandomVerticalHexes(TETile[][] world, int hexNum, Position p, int size) {
        Position temp = new Position(p);
        for (int i = 0; i < hexNum; i++) {
            TETile t = randomTile();
            drawHexagonal(temp, world, size, t);
            // update p
            System.out.println("x" + temp.x);
            System.out.println("y" + temp.y);
            temp.y -= 2 * size;
        }
    }


}