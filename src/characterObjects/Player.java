package characterObjects;

import TileMap.TileMap;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends MapObject{

    //Player Stats
    private int health, macHealth, shoot, maxFire;
    private boolean dead, recDamage;
    private long damageTime;

    //Arrows
    private boolean shooting;
    private int arrowNum, arrowDamage;
    //private ArayList<Arrow> arrows;

    //melee
    private boolean meleeAttack;
    private int meleeDamage, meleeRange;

    //Animations
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {};

    //Player Actions
    private static final int IDLE = 0;
    private static final int WALKING = 0;
    private static final int JUMPING = 0;
    private static final int FALLING = 0;
    private static final int SHOOTING = 0;
    private static final int ATTACKING = 0;



    public Player(TileMap tileMap) {
        super(tileMap);
    }
}
