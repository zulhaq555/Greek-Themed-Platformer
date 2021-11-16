package GameState;

import TileMap.TileMap;
import com.main.GamePanel;

import java.awt.*;

public class Level1State extends GameState{

    private TileMap tileMap;



    public Level1State(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        tileMap = new TileMap(32);
        tileMap.loadTiles("/level1Resources/level1Tileset.png");
        tileMap.loadMap("/level1Resources/level1Map.txt");
        tileMap.setPosition(0, 0);
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics2D g) {
        //clear screen
        g.setColor(Color.white);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        //draw tileMap
        tileMap.draw(g);
    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }
}
