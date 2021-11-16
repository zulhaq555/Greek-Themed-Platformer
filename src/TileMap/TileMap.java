package TileMap;

import com.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMap {

    //Position
    private double x, y;

    //Bounds
    private int xMin, xMax, yMin, yMax;

    //smooth scroll
    private double smoothScroll;

    //map
    private int[][] map;
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

    public void loadMap(String s){

        try{
            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;

            String delims = "\\s";

            for (int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);

                for (int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                    
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getTileSize(){
        return tileSize;
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getType(int row, int col){
        int rc = map[row][col];
        int r = rc / numTilesAcross;
        int c = rc % numTilesAcross;

        return tiles[r][c].getType();
    }

    public void setPosition(double x, double y){
        this.x += (x - this.x) * smoothScroll;
        this.y += (y - this.y) * smoothScroll;

        fixBounds();

        colOffset = (int)-this.x / tileSize;
        rowOffset = (int)-this.y / tileSize;
    }

    private void fixBounds() {

        if(x < xMin) x = xMin;
        if(y < yMin) y = yMin;
        if(x > xMax) x = xMax;
        if(y > yMax) y = yMax;

    }

    public void draw(Graphics2D g){
        for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {

            if(row >= numRows) break;

            for (int col = colOffset; col < colOffset + numColsToDraw; col++) {

                if(col >= numCols) break;

                if(map[row][col] == -1) continue;

                int rc = map[row][col];
                int r = rc / numTilesAcross;
                int c = rc % numTilesAcross;

                g.drawImage(tiles[r][c].getImage(), (int)x + col * tileSize, (int)y + row * tileSize, null);

            }
        }
    }
}
