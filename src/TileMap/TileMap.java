package TileMap;

import com.main.GamePanel;

import javax.imageio.ImageIO;
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
    private int numTilesAcross, numTileHigh;
    private Tile[][] tiles;

    //rendering
    private int rowOffset, colOffset, numRowsToDraw, numColsToDraw;

    public TileMap(int tileSize){
        this.tileSize = tileSize;
        numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
        numColsToDraw = GamePanel.WIDTH / tileSize + 2;
        smoothScroll = 0.07;
    }

    public void loadTiles(String s){
        try{
            tileSet = ImageIO.read(getClass().getResourceAsStream(s));

            numTilesAcross = tileSet.getWidth() / tileSize;
            numTileHigh = tileSet.getHeight() / tileSize;

            tiles = new Tile[numTileHigh][numTilesAcross];

            BufferedImage subImage;
            for (int col = 0; col < numTilesAcross; col++) {
                for (int row = 0; row < numTileHigh; row++) {

                    subImage = tileSet.getSubimage(col * tileSize, row * tileSize, tileSize, tileSize);
                    tiles[row][col] = new Tile(subImage, Tile.BLOCKED);

                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
