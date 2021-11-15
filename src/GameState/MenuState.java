package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState{

    private Background bg;
    private String[] options = {"Start", "Help", "Quit"};

    private int currentChoice = 0;

    private Color titleColour;
    private Font titleFont;
    private Font font;

    public MenuState(GameStateManager gsm){
        this.gsm = gsm;

        try{
            bg = new Background("/backgrounds/space3.png", 1);
            bg.setVector(-0.1, 0);

            titleColour = new Color(128, 0, 0);
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);

            font = new Font("Arial", Font.PLAIN, 12);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        bg.update();

    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);

        g.setColor(titleColour);
        g.setFont(titleFont);
        g.drawString("Rumble in Olympus", 125, 125);

        g.setFont(font);
        for(int i = 0; i < options.length; i++){
            if(i == currentChoice){
                g.setColor(Color.BLUE);
            }else{
                g.setColor(Color.RED);
            }
            g.drawString(options[i], 250, 200 + i * 15);
        }
    }

    private void select(){
        if(currentChoice == 0){

        }
        if(currentChoice == 1){

        }
        if(currentChoice == 2){
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER){
            select();
        }
        if(k == KeyEvent.VK_UP){
            currentChoice--;
            if(currentChoice == -1){
                currentChoice = options.length - 1;
            }
        }
        if(k == KeyEvent.VK_DOWN){
            currentChoice++;
            if(currentChoice == options.length){
                currentChoice = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}
