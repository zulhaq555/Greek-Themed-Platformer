package characterObjects;

import java.awt.image.BufferedImage;
import java.nio.file.FileAlreadyExistsException;

public class Animation {

    private BufferedImage[] frames;
    private int currentFrame;

    private long startTime;
    private long delay;

    private boolean cycledOnce;

    public void Animation(){
        cycledOnce = false;
    }

    public void setFrames(BufferedImage[] frames){
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
        cycledOnce = false;
    }

    public void setDelay(long delay) {this.delay = delay;}

    public void setCurrentFrame(int currentFrame) {this.currentFrame = currentFrame;}

    public void update(){
        if(delay == -1) return;

        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if(elapsed > delay){
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0;
            cycledOnce = true;
        }
    }

    public int getCurrentFrame() {return currentFrame;}

    public BufferedImage getFrames() {return frames[currentFrame];}

    public boolean isCycledOnce() {return cycledOnce;}


}
