package GameState;

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
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }
}
