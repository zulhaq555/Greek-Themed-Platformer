package TileMap;

import java.awt.image.BufferedImage;

public class TileMap {

    //Position
    private double x, y;

    //Bounds
    private int xMin, xMax, yMin, yMax;

    //smooth scroll
    private double smoothScroll;

    //map
    private int[][] mao;
    private int tileSize, numRows, numCols, width, height;

    //tileset
    private BufferedImage tileSet;
    private int numTilesAccross;
    private Tile[][] tiles;

}
