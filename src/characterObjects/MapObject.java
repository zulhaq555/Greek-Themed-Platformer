package characterObjects;

import TileMap.TileMap;
import TileMap.Tile;
import com.main.GamePanel;

import java.awt.*;
import java.util.GregorianCalendar;
import java.util.Map;

public abstract class MapObject {

    //Tile attributes
    protected TileMap tileMap;
    protected int tileSize;
    protected double xMap, yMap;

    //Position and Vector
    protected double x, y, dx, dy;

    //Dimensions
    protected int width, height;

    //Collision box
    protected int colWidth, colHeight;

    //Collision
    protected int currRow, currCol;
    protected double xDest, yDest, xTemp, yTemp;
    protected boolean topLeft, topRight, bottomLeft, bottomRight, facingRight;

    //animation
    protected Animation animation;
    protected int currentAction, previousAction;

    //movement
    protected boolean left, right, up, down, jumping, falling;

    //movement attributes/physics
    protected double moveSpeed, maxSpeed, stopSpeed, fallSpeed, maxFallSpeed, jumpStart, stopJumpSpeed;

    public MapObject(TileMap tileMap){
        this.tileMap = tileMap;
        tileSize = tileMap.getTileSize();
    }

    public boolean intersects(MapObject mapObject){
        Rectangle r1 = getRectangle();
        Rectangle r2 = mapObject.getRectangle();

        return r1.intersects(r2);
    }

    public Rectangle getRectangle(){
        return new Rectangle((int)x - colWidth, (int)y - colHeight, colWidth, colHeight);
    }

    protected void calculateCorners(double x, double y){
        int leftTile = (int)(x - colWidth / 2) / tileSize;
        int rightTile = (int)(x + colWidth / 2 -1) / tileSize;
        int topTile = (int)(y - colHeight / 2) / tileSize;
        int bottomTile = (int)(y + colHeight / 2 - 1) / tileSize;

        int tl = tileMap.getType(topTile, leftTile);
        int tr = tileMap.getType(topTile, rightTile);
        int bl = tileMap.getType(bottomTile, leftTile);
        int br = tileMap.getType(bottomTile, rightTile);

        topLeft = tl == Tile.BLOCKED;
        topRight = tr == Tile.BLOCKED;
        bottomLeft = bl == Tile.BLOCKED;
        bottomRight = br == Tile.BLOCKED;

    };

    public void checkMapCollision(){
        currCol = (int)x / tileSize;
        currRow = (int)y / tileSize;

        xDest = x + dx;
        yDest = y + dy;

        xTemp = x;
        yTemp = y;

        calculateCorners(x, yDest);
        if(dy < 0){
            if(topRight || topLeft){
                dy = 0;
                yTemp = currRow * tileSize + colHeight / 2;
            }else{
                yTemp += dy;
            }
        }
        if(dy > 0){
            if(bottomRight || bottomLeft){
                dy = 0;
                falling = false;
                yTemp = (currRow + 1) * tileSize - colHeight / 2;
            }{
                yTemp += dy;
            }
        }

        calculateCorners(xDest, y);
        if(dx < 0){
            if(topLeft || bottomLeft){
                dx = 0;
                xTemp = currCol * tileSize + colWidth / 2;
            }else{
                xTemp += dx;
            }
        }
        if(dx > 0){
            if(topRight || bottomRight){
                dx = 0;
                xTemp = (currCol + 1) * tileSize - colWidth / 2;
            }else{
                xTemp += dx;
            }
        }

        if(!falling){
            calculateCorners(x, yDest + 1);
            if(!bottomRight && !bottomLeft){
                falling = true;
            }
        }
    }

    public double getX() {return x;}

    public double getY() {return y;}

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    public int getColWidth() {return colWidth;}

    public int getColHeight() {return colHeight;}

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setVector(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    public void setMapPosition(){
        xMap = tileMap.getX();
        yMap = tileMap.getY();
    }

    public void setLeft(boolean left) {this.left = left;}

    public void setRight(boolean right) {this.right = right;}

    public void setUp(boolean up) {this.up = up;}

    public void setDown(boolean down) {this.down = down;}

    public void setJumping(boolean jumping) {this.jumping = jumping;}

    public boolean NotOnScreen(){
        return x + xMap + width < 0 || x + xMap - width > GamePanel.WIDTH  ||
        y + yMap + height < 0 || y + yMap - height > GamePanel.HEIGHT;

    }


}
